package com.example.assuremyevent.service;

import com.example.assuremyevent.model.dto.request.EventRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface EventService {
    ResponseEntity addEvent(EventRequestDto eventRequest);

    ResponseEntity getEvents(int organizerId);

    ResponseEntity updateEvent(EventRequestDto eventRequest);

    ResponseEntity deleteEvent(int eventId);

    ResponseEntity manageStatus(String bookingStatus, int bookingId, int organizerId);

    ResponseEntity getBookings(int organizerId);

    ResponseEntity getFeedback(int organizerId);
}