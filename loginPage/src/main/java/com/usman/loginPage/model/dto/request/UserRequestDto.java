package com.usman.loginPage.model.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRequestDto {

    @Email(message = "invalid email id format")
    @NotEmpty(message = "email cannot be empty")
    private String userEmail;

    @NotEmpty(message = "UserName cannot be empty")
    private String userName;

    @Size(min = 8, max = 12, message = "Password length should be 8-12")
    @NotEmpty(message = "password cannot be empty")
    private String password;
}