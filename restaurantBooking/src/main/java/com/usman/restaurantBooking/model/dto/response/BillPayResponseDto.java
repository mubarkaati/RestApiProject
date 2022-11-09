package com.usman.restaurantBooking.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillPayResponseDto {
    private int billId;
    private String personName;
    private boolean isPaid;
}