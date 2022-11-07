package com.example.assuremyevent.controller;

import com.example.assuremyevent.model.dto.EventCategoryDto;
import com.example.assuremyevent.service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    EventCategoryService eventCategoryService;

    @PostMapping("/add")
    public ResponseEntity addEventCategory(@RequestBody EventCategoryDto eventCategory) {
        return eventCategoryService.addCategory(eventCategory);
    }

    @GetMapping("/get")
    public ResponseEntity getEventCategory() {
        return eventCategoryService.getEventCategories();
    }
}