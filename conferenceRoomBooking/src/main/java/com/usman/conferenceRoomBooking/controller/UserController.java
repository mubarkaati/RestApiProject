package com.usman.conferenceRoomBooking.controller;

import com.usman.conferenceRoomBooking.model.dto.request.BookingRequestDto;
import com.usman.conferenceRoomBooking.service.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    RoomBookingService roomBookingService;

    @PostMapping("/createBooking")
    public ResponseEntity bookRoom(@RequestBody BookingRequestDto bookingRequest) {
        try {
            return roomBookingService.createBooking(bookingRequest);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}