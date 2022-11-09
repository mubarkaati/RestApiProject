package com.usman.conferenceRoomBooking.model.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class BookingResponseDto {
    private int bookingId;
    private Date startDateAndTime;
    private Date endDateAndTime;
    private String roomName;
    private String employeeName;
}