package com.usman.conferenceRoomBooking.model.dto.request;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class BookingRequestDto {
    private int roomId;
    private String startDateAndTime;
    private String endDateAndTime;
    private int employeeId;
}