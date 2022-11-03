package com.example.assuremyevent.service;

import com.example.assuremyevent.entities.Event;
import com.example.assuremyevent.model.dto.EventDto;
import com.example.assuremyevent.repository.EventCategoryRepository;
import com.example.assuremyevent.repository.EventOrganizerRepository;
import com.example.assuremyevent.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventOrganizerService {

    @Autowired
    EventOrganizerRepository eventOrganizerRepository;
    
    @Autowired
    EventCategoryRepository eventCategoryRepository;

    public Event addEvent(EventDto eventDto) {
        Event event = new Event();
        event.setEventName(eventDto.getEventName());
        event.setEventVenue(eventDto.getEventVenue());
        event.setEventBasePrice(eventDto.getEventBasePrice());
        event.setEventCity(eventDto.getEventCity());
        event.setEventCategory(eventCategoryRepository.findById(eventDto.getEventCategoryId()).orElse(null));
        event.setEventOrganizer();
    }
}