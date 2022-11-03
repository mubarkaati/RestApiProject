package com.example.assuremyevent.controller;

import com.example.assuremyevent.entities.EventCategory;
import com.example.assuremyevent.service.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    EventCategoryService eventCategoryService;

    @PostMapping("/addEventCategory")
    public ResponseEntity addEventCategory(@RequestBody EventCategory eventCategory) {
        try {
            EventCategory eventCategory1 = eventCategoryService.addEventCategory(eventCategory);
            if (eventCategory != null)
                return new ResponseEntity(Optional.of(eventCategory1), HttpStatus.OK);
            else
                return new ResponseEntity(Optional.of(null), HttpStatus.NOT_ACCEPTABLE);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEventCategories")
    public ResponseEntity getEventCategories() {
        try {
            List<EventCategory> eventCategories = eventCategoryService.getEventCategories();
            if (eventCategories.size() > 0)
                return new ResponseEntity(Optional.of(eventCategories), HttpStatus.OK);
            else
                return new ResponseEntity(Optional.of(null), HttpStatus.NO_CONTENT);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/updateEventCategory")
    public ResponseEntity updateEventCategory(@RequestBody EventCategory eventCategory) {
        try {
            EventCategory eventCategory1 = eventCategoryService.updateEventCategory(eventCategory);
            if (eventCategory1 != null)
                return new ResponseEntity(Optional.of(eventCategory1), HttpStatus.OK);
            else
                return new ResponseEntity(Optional.of(null), HttpStatus.NOT_FOUND);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEventCategory/{eventCategoryId}")
    public ResponseEntity deleteEventCategory(@PathVariable String eventCategoryId) {
        try {
            Boolean status = eventCategoryService.deleteEventCategory(Integer.parseInt(eventCategoryId));
            if (status == true)
                return new ResponseEntity(HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}