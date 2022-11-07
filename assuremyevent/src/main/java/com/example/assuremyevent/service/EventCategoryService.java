package com.example.assuremyevent.service;

import com.example.assuremyevent.model.dto.EventCategoryDto;
import org.springframework.http.ResponseEntity;

public interface EventCategoryService {
    ResponseEntity addCategory(EventCategoryDto eventCategory);
    ResponseEntity getEventCategories();
}