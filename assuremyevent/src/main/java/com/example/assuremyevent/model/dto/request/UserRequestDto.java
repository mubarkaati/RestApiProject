package com.example.assuremyevent.model.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private int userId;
    private String username;
    private String userEmail;
    private String userPassword;
    private String userRole;
}