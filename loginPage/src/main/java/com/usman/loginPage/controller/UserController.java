package com.usman.loginPage.controller;

import com.usman.loginPage.model.dto.request.UserRequestDto;
import com.usman.loginPage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity(userService.registerUser(userRequestDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity(userService.loginUser(userRequestDto), HttpStatus.OK);
    }
}