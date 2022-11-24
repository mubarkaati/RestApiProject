package com.usman.loginPage.service;

import com.usman.loginPage.entities.UserTable;
import com.usman.loginPage.exception.InvalidCredential;
import com.usman.loginPage.model.dto.request.UserRequestDto;
import com.usman.loginPage.model.dto.response.UserResponseDto;
import com.usman.loginPage.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        UserTable userTable = this.modelMapper.map(userRequestDto, UserTable.class);
        return (this.modelMapper.map(userRepository.save(userTable), UserResponseDto.class));
    }

    @Override
    public UserResponseDto loginUser(UserRequestDto userRequestDto) {
        UserTable userTable = userRepository.findByUserEmailAndPassword(userRequestDto.getUserEmail(), userRequestDto.getPassword());
        if (userTable != null) {
            return this.modelMapper.map(userTable, UserResponseDto.class);
        } else {
            throw new InvalidCredential();
        }
    }
}