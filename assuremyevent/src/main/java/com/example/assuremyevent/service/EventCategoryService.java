package com.example.assuremyevent.service;

import com.example.assuremyevent.entities.EventCategory;
import com.example.assuremyevent.repository.EventCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCategoryService {
    @Autowired
    EventCategoryRepository eventCategoryRepository;

    public EventCategory addEventCategory(EventCategory eventCategory) {
        return eventCategoryRepository.save(eventCategory);
    }

    public List<EventCategory> getEventCategories() {
        return eventCategoryRepository.findAll();
    }

    public EventCategory updateEventCategory(EventCategory eventCategory) {
        EventCategory eventCategory1 = eventCategoryRepository.findById(eventCategory.getEventCategoryId()).orElse(null);
        if (eventCategory1 != null) {
            eventCategory1.setEventCategoryName(eventCategory.getEventCategoryName());
            return eventCategoryRepository.save(eventCategory1);
        }
        else {
            return null;
        }
    }

    public Boolean deleteEventCategory(Integer eventCategoryId) {
        EventCategory eventCategory = eventCategoryRepository.findById(eventCategoryId).orElse(null);
        if (eventCategory != null) {
            eventCategoryRepository.delete(eventCategory);
            return true;
        }
        else {
            return false;
        }
    }
}