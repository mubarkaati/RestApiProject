package com.example.assuremyevent.service;

import com.example.assuremyevent.model.dto.BookingDto;
import com.example.assuremyevent.model.dto.request.FeedbackRequestDto;
import org.springframework.http.ResponseEntity;

public interface BookingService {
    ResponseEntity createBooking(BookingDto booking);

    ResponseEntity deleteBooking(int bookingId, int customerId);

    ResponseEntity getBookings(int customerId);

    ResponseEntity showEvents();

    ResponseEntity addFeedback(FeedbackRequestDto feedbackRequestDto);
}
