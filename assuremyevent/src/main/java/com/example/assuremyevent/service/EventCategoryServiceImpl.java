package com.example.assuremyevent.service;

import com.example.assuremyevent.entities.EventCategory;
import com.example.assuremyevent.model.dto.EventCategoryDto;
import com.example.assuremyevent.repository.EventCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventCategoryServiceImpl implements EventCategoryService {

    @Autowired
    EventCategoryRepository categoryRepository;

    @Override
    public ResponseEntity addCategory(EventCategoryDto categoryDto) {
        try {
            EventCategory eventCategory = new EventCategory();
            eventCategory.setEventCategoryName(categoryDto.getEventCategoryName());
            EventCategory savedCategory = categoryRepository.save(eventCategory);
            if (savedCategory != null) {
                EventCategoryDto eventCategoryDto = new EventCategoryDto();
                eventCategoryDto.setEventCategoryId(savedCategory.getEventCategoryId());
                eventCategoryDto.setEventCategoryName(savedCategory.getEventCategoryName());
                return new ResponseEntity(Optional.of(eventCategoryDto), HttpStatus.OK);
            }
            else {
                return new ResponseEntity("Cannot add event category", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity getEventCategories() {
        try {
            List<EventCategory> eventCategories = categoryRepository.findAll();
            if (!eventCategories.isEmpty()) {
                return new ResponseEntity(
                        eventCategories.stream()
                        .map(eventCategory -> new EventCategoryDto(
                                eventCategory.getEventCategoryId(),
                                eventCategory.getEventCategoryName()
                        ))
                        .collect(Collectors.toList()), HttpStatus.OK);
            }
            else {
                return new ResponseEntity("No Event Categories Exist", HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}