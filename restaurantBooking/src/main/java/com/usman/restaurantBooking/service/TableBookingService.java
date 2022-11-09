package com.usman.restaurantBooking.service;

import com.usman.restaurantBooking.entities.TableBooking;
import com.usman.restaurantBooking.model.dto.request.TableBookingRequestDto;
import com.usman.restaurantBooking.model.dto.response.TableBookingResponseDto;

import java.util.HashMap;
import java.util.Map;

public interface TableBookingService {
    public TableBookingResponseDto addTableBooking(TableBookingRequestDto bookingRequestDto);
}