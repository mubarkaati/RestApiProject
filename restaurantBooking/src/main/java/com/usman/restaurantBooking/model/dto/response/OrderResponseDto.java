package com.usman.restaurantBooking.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {
    private int orderId;
    private int tableId;
    private List<String> menuItemName;
}