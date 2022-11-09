package com.usman.restaurantBooking.service;

import com.usman.restaurantBooking.model.dto.request.OrderRequestDto;
import com.usman.restaurantBooking.model.dto.response.OrderResponseDto;

public interface OrderService {
    public OrderResponseDto addOrder(OrderRequestDto requestDto);
}
