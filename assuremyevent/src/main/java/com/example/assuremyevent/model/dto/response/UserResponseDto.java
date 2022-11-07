package com.example.assuremyevent.model.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private int userId;
    private String userName;
    private String userRole;
}