package com.example.assuremyevent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private int bookingId;
    private float totalPrice;
    private int numberOfGuest;
    private Time startTime;
    private Time endTime;
    private Date startDate;
    private Date endDate;
    private String bookingStatus;
    private int eventId;
    private int customerId;
}