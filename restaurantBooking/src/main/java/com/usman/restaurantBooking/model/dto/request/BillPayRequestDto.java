package com.usman.restaurantBooking.model.dto.request;

import lombok.Data;

@Data
public class BillPayRequestDto {
    private int billId;
    private float paidAmount;
}