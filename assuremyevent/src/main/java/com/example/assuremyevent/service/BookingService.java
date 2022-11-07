package com.example.assuremyevent.service;

import com.example.assuremyevent.model.dto.BookingDto;
import com.example.assuremyevent.model.dto.FeedbackDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface BookingService {
    ResponseEntity createBooking(BookingDto booking, int customerId);

    ResponseEntity deleteBooking(int bookingId, int customerId);

    ResponseEntity getBookings(int customerId);

    ResponseEntity showEvents();

    ResponseEntity addFeedback(FeedbackDto feedbackDto);
}
