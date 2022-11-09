package com.usman.conferenceRoomBooking.controller;

import com.usman.conferenceRoomBooking.entities.ConferenceRoom;
import com.usman.conferenceRoomBooking.entities.Employee;
import com.usman.conferenceRoomBooking.service.ConferenceRoomService;
import com.usman.conferenceRoomBooking.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    ConferenceRoomService conferenceRoomService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/addRoom")
    public ResponseEntity addRoom(@RequestBody ConferenceRoom room) {
        try {
            return new ResponseEntity(Optional.of(conferenceRoomService.addRoom(room)), HttpStatus.CREATED);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addEmployee")
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        try {
            return new ResponseEntity(Optional.of(employeeService.addEmployee(employee)), HttpStatus.CREATED);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}