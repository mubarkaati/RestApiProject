package com.example.assuremyevent.service;

import com.example.assuremyevent.entities.Booking;
import com.example.assuremyevent.entities.Event;
import com.example.assuremyevent.entities.User;
import com.example.assuremyevent.model.dto.request.EventRequestDto;
import com.example.assuremyevent.model.dto.response.EventResponseDto;
import com.example.assuremyevent.model.dto.response.FeedbackResponseDto;
import com.example.assuremyevent.repository.BookingRepository;
import com.example.assuremyevent.repository.EventCategoryRepository;
import com.example.assuremyevent.repository.EventRepository;
import com.example.assuremyevent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    EventCategoryRepository categoryRepository;

    @Override
    public ResponseEntity addEvent(EventRequestDto eventRequest) {
        try {
            Event event = new Event();
            event.setEventCity(eventRequest.getEventCity());
            event.setEventName(eventRequest.getEventName());
            event.setEventVenue(eventRequest.getEventVenue());
            event.setPricePerPerson(eventRequest.getPricePerPerson());
            event.setUser(userRepository.findById(eventRequest.getEventOrganizerId()).orElse(null));
            event.setEventBasePrice(eventRequest.getEventBasePrice());
            event.setEventCategory(categoryRepository.findById(eventRequest.getEventCategoryId()).orElse(null));
            Event savedEvent = eventRepository.save(event);
            if (savedEvent != null) {
                EventResponseDto eventResponse = new EventResponseDto();
                eventResponse.setEventId(savedEvent.getEventId());
                eventResponse.setEventName(savedEvent.getEventName());
                eventResponse.setEventCity(savedEvent.getEventCity());
                eventResponse.setEventVenue(savedEvent.getEventVenue());
                eventResponse.setPricePerPerson(savedEvent.getPricePerPerson());
                eventResponse.setAverageFeedbackRating(savedEvent.getAverageFeedbackRating());
                eventResponse.setEventBasePrice(savedEvent.getEventBasePrice());
                return new ResponseEntity(Optional.of(eventResponse), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getEvents(int organizerId) {
        try {
            List<Event> eventList = eventRepository.findAll();
            List<Event> events = eventList.stream()
                    .filter(event -> event.getUser().getUserId() == organizerId)
                    .collect(Collectors.toList());
            if (!events.isEmpty()) {
                return new ResponseEntity(
                        events.stream()
                                .map(event -> new EventResponseDto(
                                        event.getEventId(),
                                        event.getEventName(),
                                        event.getEventVenue(),
                                        event.getEventCity(),
                                        event.getEventBasePrice(),
                                        event.getPricePerPerson(),
                                        event.getAverageFeedbackRating()
                                )).collect(Collectors.toList()), HttpStatus.OK);
            } else {
                return new ResponseEntity("You Have No Events", HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity updateEvent(EventRequestDto eventRequest) {
        try {
            Event existingEvent = eventRepository.findById(eventRequest.getEventId()).orElse(null);
            if (existingEvent != null) {
                existingEvent.setEventCity(eventRequest.getEventCity());
                existingEvent.setEventName(eventRequest.getEventName());
                existingEvent.setEventVenue(eventRequest.getEventVenue());
                existingEvent.setPricePerPerson(eventRequest.getPricePerPerson());
                existingEvent.setUser(userRepository.findById(eventRequest.getEventOrganizerId()).orElse(null));
                existingEvent.setEventBasePrice(eventRequest.getEventBasePrice());
                existingEvent.setEventCategory(categoryRepository.findById(eventRequest.getEventCategoryId()).orElse(null));
                Event savedEvent = eventRepository.save(existingEvent);
                return new ResponseEntity(Optional.of(savedEvent), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity deleteEvent(int eventId) {
        try {
            Event existingEvent = eventRepository.findById(eventId).orElse(null);
            if (existingEvent != null) {
                eventRepository.delete(existingEvent);
                return new ResponseEntity("Record Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity("Invalid EventId", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity manageStatus(String bookingStatus, int bookingId, int organizerId) {
        try {
            Booking existingBooking = bookingRepository.findById(bookingId).orElse(null);
            System.out.println(existingBooking.getEvent().getUser().getUserId());
            if ((existingBooking != null) && (existingBooking.getEvent().getUser().getUserId() == organizerId) && (existingBooking.isDeleted() == false)) {
                existingBooking.setBookingStatus(bookingStatus);
                bookingRepository.save(existingBooking);
                return new ResponseEntity("Booking Status Changed Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity("Invalid Booking Id", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getBookings(int organizerId) {
        try {
            User user = userRepository.findById(organizerId).orElse(null);
            if (user != null) {
                if (user.getBookings().size() > 0) {
                    return new ResponseEntity(Optional.of(user.getBookings()), HttpStatus.OK);
                } else {
                    return new ResponseEntity("don't have any booking", HttpStatus.NO_CONTENT);
                }
            } else {
                return new ResponseEntity("Invalid User Id", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getFeedback(int organizerId) {
        try {
            List<Event> events = eventRepository.findAll();
            if ((!events.isEmpty()) && (events.stream().filter(event -> event.getUser().getUserId() == organizerId).collect(Collectors.toList())).size() > 0) {
                return new ResponseEntity(Optional.of(events.stream()
                        .filter(event -> event.getUser().getUserId() == organizerId)
                        .collect(Collectors.toList())), HttpStatus.OK);
            } else {
                return new ResponseEntity("No Content Available", HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}