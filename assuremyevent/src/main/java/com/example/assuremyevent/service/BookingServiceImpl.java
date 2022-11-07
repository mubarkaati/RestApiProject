package com.example.assuremyevent.service;

import com.example.assuremyevent.entities.Booking;
import com.example.assuremyevent.entities.Event;
import com.example.assuremyevent.entities.Feedback;
import com.example.assuremyevent.model.dto.BookingDto;
import com.example.assuremyevent.model.dto.request.FeedbackRequestDto;
import com.example.assuremyevent.model.dto.response.EventResponseDto;
import com.example.assuremyevent.repository.BookingRepository;
import com.example.assuremyevent.repository.EventRepository;
import com.example.assuremyevent.repository.FeedbackRepository;
import com.example.assuremyevent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public ResponseEntity createBooking(BookingDto booking) {
        try {
            Booking newBooking = new Booking();
            newBooking.setBookingStatus("Pending");
            newBooking.setNumberOfGuest(booking.getNumberOfGuest());
            newBooking.setEndTime(booking.getEndTime());
            newBooking.setStartTime(booking.getStartTime());
            newBooking.setEndDate(booking.getEndDate());
            newBooking.setStartDate(booking.getStartDate());
            Event event = eventRepository.findById(booking.getEventId()).orElse(null);
            newBooking.setEvent(event);
            newBooking.setUser(userRepository.findById(booking.getCustomerId()).orElse(null));
            newBooking.setTotalPrice(event.getEventBasePrice() + (booking.getNumberOfGuest()) * event.getPricePerPerson());

            //Booking Created
            Booking bookingCreated = bookingRepository.save(newBooking);
            if (bookingCreated != null) {
                BookingDto bookingDto = new BookingDto();
                bookingDto.setBookingId(bookingCreated.getBookingId());
                bookingDto.setBookingStatus(bookingCreated.getBookingStatus());
                bookingDto.setEndDate(bookingCreated.getEndDate());
                bookingDto.setStartDate(bookingCreated.getStartDate());
                bookingDto.setEndTime(bookingCreated.getEndTime());
                bookingDto.setStartTime(bookingCreated.getStartTime());
                bookingDto.setTotalPrice(bookingCreated.getTotalPrice());
                bookingDto.setNumberOfGuest(bookingCreated.getNumberOfGuest());
                bookingDto.setEventId(bookingCreated.getEvent().getEventId());
                bookingDto.setCustomerId(bookingCreated.getUser().getUserId());
                return new ResponseEntity(Optional.of(bookingDto), HttpStatus.CREATED);
            } else {
                return new ResponseEntity("Cannot Create Booking", HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity deleteBooking(int bookingId, int customerId) {
        try {
            Booking booking = bookingRepository.findById(bookingId).orElse(null);
            if (booking != null) {
                if (booking.isDeleted() && booking.getUser().getUserId() == customerId) {
                    return new ResponseEntity("Booking is Already Deleted",new HttpHeaders(),HttpStatus.NOT_MODIFIED);
                } else if (booking.getUser().getUserId() == customerId) {
                    booking.setDeleted(true);
                    return new ResponseEntity("Deleted Successfully", HttpStatus.ACCEPTED);
                } else {
                    return new ResponseEntity("No Booking Exist With This bookingId", HttpStatus.NO_CONTENT);
                }
            } else {
                return new ResponseEntity("Invalid Booking Id", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getBookings(int customerId) {
        List<Booking> bookings = bookingRepository.findAll();
        if (!bookings.isEmpty()) {
            List<Booking> bookingList = bookings.stream()
                    .filter(booking -> booking.getUser().getUserId() == customerId)
                    .collect(Collectors.toList());
            if (!bookingList.isEmpty()) {
                return new ResponseEntity(Optional.of(bookingList.stream()
                        .map(booking -> new BookingDto(
                                booking.getBookingId(),
                                booking.getTotalPrice(),
                                booking.getNumberOfGuest(),
                                booking.getStartTime(),
                                booking.getEndTime(),
                                booking.getStartDate(),
                                booking.getEndDate(),
                                booking.getBookingStatus(),
                                booking.getEvent().getEventId(),
                                booking.getUser().getUserId(),
                                booking.isDeleted()
                        ))), HttpStatus.OK);
            } else {
                return new ResponseEntity("You Don't Have Any Booking", HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity("No Booking available", HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity showEvents() {
        try {
            List<Event> events = eventRepository.findAll();
            if (!events.isEmpty()) {
                return new ResponseEntity(Optional.of(events.stream()
                        .map(event -> new EventResponseDto(
                                event.getEventId(),
                                event.getEventName(),
                                event.getEventVenue(),
                                event.getEventCity(),
                                event.getEventBasePrice(),
                                event.getPricePerPerson(),
                                event.getAverageFeedbackRating()
                        )).collect(Collectors.toList())),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity("No Events Available", HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity addFeedback(FeedbackRequestDto feedbackRequestDto) {
        try {
            Feedback feedback = new Feedback();
            feedback.setFeedbackRating(feedbackRequestDto.getFeedbackRating());
            feedback.setFeedbackComment(feedbackRequestDto.getFeedbackComment());
            feedback.setEvent(eventRepository.findById(feedbackRequestDto.getEventId()).orElse(null));
            feedback.setUser(userRepository.findById(feedbackRequestDto.getCustomerId()).orElse(null));

            Event event = eventRepository.findById(feedbackRequestDto.getEventId()).orElse(null);
            event.setAverageFeedbackRating((event.getAverageFeedbackRating() + feedbackRequestDto.getFeedbackRating()) / 2);
            eventRepository.save(event);

            return new ResponseEntity(Optional.of(feedbackRepository.save(feedback)), HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}