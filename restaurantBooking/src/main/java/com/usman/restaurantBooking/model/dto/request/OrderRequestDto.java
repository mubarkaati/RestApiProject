package com.usman.restaurantBooking.model.dto.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OrderRequestDto {
    private int tableId;
    private Map<String, Integer> menuListAndQuantity;
}