package com.example.assuremyevent.service;

import com.example.assuremyevent.model.dto.request.UserRequestDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity addUser(UserRequestDto user);
    ResponseEntity LoginUser(UserRequestDto user);
}