package com.usman.conferenceRoomBooking.service;

import com.usman.conferenceRoomBooking.entities.RoomBooking;
import com.usman.conferenceRoomBooking.model.dto.request.BookingRequestDto;
import com.usman.conferenceRoomBooking.repository.BookingRepository;
import com.usman.conferenceRoomBooking.repository.EmployeeRepository;
import com.usman.conferenceRoomBooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public ResponseEntity createBooking(BookingRequestDto bookingRequest) {

        HashMap<String, Object> tableStatus = new HashMap<>();

        if ((roomRepository.findById(bookingRequest.getRoomId()).isPresent())
                && (employeeRepository.findById(bookingRequest.getEmployeeId()).isPresent())) {

            List<RoomBooking> roomBookings = bookingRepository
                    .findByRoom(roomRepository.findById(bookingRequest.getRoomId())
                            .orElse(null));

            Locale locale = Locale.ENGLISH;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss a", locale);
            Date startDate;
            Date endDate;
            try {
                startDate = simpleDateFormat.parse(bookingRequest.getStartDateAndTime());
                endDate = simpleDateFormat.parse(bookingRequest.getEndDateAndTime());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            long epochStartTime = startDate.getTime();
            long epochEndTime = endDate.getTime();

            if (roomBookings.size() > 0) {
                List<RoomBooking> roomBookingList = roomBookings.stream()
                        .filter(roomBooking ->
                                (roomBooking.getStartDateAndTime() <= epochStartTime
                                        && roomBooking.getEndDateAndTime() >= epochStartTime) ||
                                        (roomBooking.getStartDateAndTime() <= epochEndTime
                                                && roomBooking.getEndDateAndTime() >= epochEndTime) ||
                                        (roomBooking.getStartDateAndTime() >= epochStartTime
                                                && roomBooking.getEndDateAndTime() <= epochEndTime)
                        ).collect(Collectors.toList());
                if (roomBookingList.size() > 0) {
                    tableStatus.put("message", "Slot is not available book another slot");
                    return new ResponseEntity(tableStatus, HttpStatus.NOT_ACCEPTABLE);
                }
            }
            RoomBooking roomBooking = new RoomBooking();
            roomBooking.setRoom(roomRepository.findById(bookingRequest.getRoomId()).orElse(null));
            roomBooking.setBookingEmployee(employeeRepository.findById(bookingRequest.getEmployeeId()).orElse(null));
            roomBooking.setStartDateAndTime(epochStartTime);
//            Date date = new Date(epochEndTime);
//            Date date1 = new Date(epochStartTime);
//            System.out.println(date1);
//            System.out.println(date);
            roomBooking.setEndDateAndTime(epochEndTime);
            return new ResponseEntity(bookingRepository.save(roomBooking), HttpStatus.OK);
        } else {
            tableStatus.put("message", "Invalid RoomId or EmployeeId");
            return new ResponseEntity(tableStatus, HttpStatus.OK);
        }
    }
}