package com.usman.restaurantBooking.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBillResponseDto {
    private int billId;
    private int tableId;
    private float totalAmount;
    private String personName;
}