package com.example.assuremyevent.controller;

import com.example.assuremyevent.model.dto.BookingDto;
import com.example.assuremyevent.model.dto.request.FeedbackRequestDto;
import com.example.assuremyevent.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/addBooking")
    public ResponseEntity addBooking(@RequestBody BookingDto booking) {
        return bookingService.createBooking(booking);
    }

    @DeleteMapping("/deleteBooking/{bookingId}/{customerId}")
    public ResponseEntity cancelBooking(@PathVariable int bookingId, @PathVariable int customerId) {
        return bookingService.deleteBooking(bookingId, customerId);
    }

    @GetMapping("/getBookings/{customerId}")
    public ResponseEntity getBookings(@PathVariable int customerId) {
        return bookingService.getBookings(customerId);
    }

    @GetMapping("/getEvents")
    public ResponseEntity showEvents() {
        return bookingService.showEvents();
    }

    @PostMapping("/addFeedback")
    public ResponseEntity addFeedback(@RequestBody FeedbackRequestDto feedbackRequestDto) {
        return bookingService.addFeedback(feedbackRequestDto);
    }
}