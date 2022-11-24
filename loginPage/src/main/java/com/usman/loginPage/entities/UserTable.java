package com.usman.loginPage.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
public class UserTable {
    @Id
//    @Email(message = "invalid email id format")
//    @NotEmpty(message = "email cannot be empty")
    private String userEmail;

//    @NotEmpty(message = "UserName cannot be empty")
    private String userName;

//    @Size(min = 8, max = 12, message = "Password length should be 8-12")
//    @NotEmpty(message = "password cannot be empty")
    private String password;
}