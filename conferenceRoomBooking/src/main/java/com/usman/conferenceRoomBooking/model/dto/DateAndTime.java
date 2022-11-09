package com.usman.conferenceRoomBooking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateAndTime {
    private long startDateAndTime;
    private long endDateAndTime;
}