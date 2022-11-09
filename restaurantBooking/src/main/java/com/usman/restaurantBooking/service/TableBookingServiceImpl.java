package com.usman.restaurantBooking.service;

import com.usman.restaurantBooking.entities.TableBooking;
import com.usman.restaurantBooking.entities.Tables;
import com.usman.restaurantBooking.model.dto.request.TableBookingRequestDto;
import com.usman.restaurantBooking.model.dto.response.TableBookingResponseDto;
import com.usman.restaurantBooking.repository.TableBookingRepository;
import com.usman.restaurantBooking.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableBookingServiceImpl implements TableBookingService {

    @Autowired
    TablesRepository tablesRepository;

    @Autowired
    TableBookingRepository tableBookingRepository;

    @Override
    public TableBookingResponseDto addTableBooking(TableBookingRequestDto bookingRequestDto) {
        List<Tables> tables = tablesRepository.findAvailableTables(bookingRequestDto.getNumberOfPersons(), 0);
        if ((tables.size() > 0) && (bookingRequestDto.getNumberOfPersons() > 0)) {
                Tables availableTable = tables.get(0);

                //setting properties of new booking
                TableBooking tableBooking = new TableBooking();
                tableBooking.setTable(availableTable);
                tableBooking.setNumberOfPersons(bookingRequestDto.getNumberOfPersons());
                tableBooking.setPersonName(bookingRequestDto.getPersonName());
                tableBooking.setPhoneNumber(bookingRequestDto.getPhoneNumber());

                //saved new table booking
                TableBooking bookedTable = tableBookingRepository.save(tableBooking);

                //setting available quantity of particular table
                availableTable.setAvailableTable(availableTable.getAvailableTable() - 1);
                tablesRepository.save(availableTable);

                //setting response dto
                TableBookingResponseDto bookingResponseDto = new TableBookingResponseDto();
                bookingResponseDto.setTableId(bookedTable.getTableId());
                bookingResponseDto.setPersonName(bookedTable.getPersonName());

                return bookingResponseDto;
        }
        else {
            return null;
        }
    }
}