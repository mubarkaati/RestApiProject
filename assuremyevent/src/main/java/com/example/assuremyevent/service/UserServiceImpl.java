package com.example.assuremyevent.service;

import com.example.assuremyevent.entities.User;
import com.example.assuremyevent.model.dto.request.UserRequestDto;
import com.example.assuremyevent.model.dto.response.UserResponseDto;
import com.example.assuremyevent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity addUser(UserRequestDto user) {
        try {
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setUserEmail(user.getUserEmail());
            newUser.setUserPassword(user.getUserPassword());
            newUser.setUserRole(user.getUserRole());

            User savedUser = userRepository.save(newUser);

            if (savedUser != null) {
                UserResponseDto userResponseDto = new UserResponseDto();
                userResponseDto.setUserId(savedUser.getUserId());
                userResponseDto.setUserName(savedUser.getUsername());
                userResponseDto.setUserRole(savedUser.getUserRole());
                return new ResponseEntity(Optional.of(userResponseDto), HttpStatus.CREATED);
            } else {
                return new ResponseEntity("Cannot add user", HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity LoginUser(UserRequestDto user) {
        try {
            User existingUser = userRepository.findByUserEmail(user.getUserEmail());
            if ((existingUser != null) && (user.getUserPassword().equals(existingUser.getUserPassword()))) {
                UserResponseDto userResponse = new UserResponseDto();
                userResponse.setUserId(existingUser.getUserId());
                userResponse.setUserName(existingUser.getUsername());
                userResponse.setUserRole(existingUser.getUserRole());

                return new ResponseEntity(Optional.of(userResponse), HttpStatus.OK);
            }
            return new ResponseEntity("Invalid Email and Password", HttpStatus.FORBIDDEN);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}