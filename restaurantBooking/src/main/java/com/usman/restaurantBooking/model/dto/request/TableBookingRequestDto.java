package com.usman.restaurantBooking.model.dto.request;

import lombok.Data;

@Data
public class TableBookingRequestDto {
    private String personName;
    private int numberOfPersons;
    private long phoneNumber;
}