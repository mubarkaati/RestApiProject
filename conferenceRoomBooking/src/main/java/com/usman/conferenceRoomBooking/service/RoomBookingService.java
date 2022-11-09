package com.usman.conferenceRoomBooking.service;

import com.usman.conferenceRoomBooking.model.dto.request.BookingRequestDto;
import com.usman.conferenceRoomBooking.model.dto.response.BookingResponseDto;
import org.springframework.http.ResponseEntity;

public interface RoomBookingService {
    public ResponseEntity createBooking(BookingRequestDto bookingRequest);
}