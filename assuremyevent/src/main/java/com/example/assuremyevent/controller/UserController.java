package com.example.assuremyevent.controller;

import com.example.assuremyevent.model.dto.request.UserRequestDto;
import com.example.assuremyevent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequest) {
        return userService.addUser(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserRequestDto userRequest) {
        return userService.LoginUser(userRequest);
    }
}