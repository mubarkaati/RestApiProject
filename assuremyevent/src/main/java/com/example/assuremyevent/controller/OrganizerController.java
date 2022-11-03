package com.example.assuremyevent.controller;

import com.example.assuremyevent.model.dto.EventDto;
import com.example.assuremyevent.service.EventOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    EventOrganizerService eventOrganizerService;

    public ResponseEntity createEvent(EventDto eventDto) {
        try {
            eventOrganizerService
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
