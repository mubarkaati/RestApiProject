package com.example.assuremyevent.controller;

import com.example.assuremyevent.model.dto.request.EventRequestDto;
import com.example.assuremyevent.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventOrganizer")
public class EventOrganizerController {

    @Autowired
    EventService eventService;

    @PostMapping("/addEvent")
    public ResponseEntity addEvent(@RequestBody EventRequestDto eventRequest) {
        return eventService.addEvent(eventRequest);
    }

    @GetMapping("/getEvents/{organizerId}")
    public ResponseEntity getEvents(@PathVariable int organizerId) {
        return eventService.getEvents(organizerId);
    }

    @PatchMapping("/updateEvent")
    public ResponseEntity updateEvent(@RequestBody EventRequestDto eventRequest) {
        return eventService.updateEvent(eventRequest);
    }

    @DeleteMapping("/deleteEvent/{eventId}")
    public ResponseEntity deleteEvent(@PathVariable int eventId) {
        return eventService.deleteEvent(eventId);
    }

    @PatchMapping("/manageBookingStatus/{bookingStatus}/{bookingId}/{organizerId}")
    public ResponseEntity manageBookingStatus(@PathVariable String bookingStatus, @PathVariable int bookingId, @PathVariable int organizerId) {
        return eventService.manageStatus(bookingStatus, bookingId, organizerId);
    }

    @GetMapping("/getBookings/{organizerId}")
    public ResponseEntity getBookings(@PathVariable int organizerId) {
        return eventService.getBookings(organizerId);
    }

    @GetMapping("/getFeedbacks/{organizerId}")
    public ResponseEntity getFeedback(@PathVariable int organizerId) {
        return eventService.getFeedback(organizerId);
    }
}