package com.usman.loginPage.service;

import com.usman.loginPage.model.dto.request.UserRequestDto;
import com.usman.loginPage.model.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);

    UserResponseDto loginUser(UserRequestDto userRequestDto);
}