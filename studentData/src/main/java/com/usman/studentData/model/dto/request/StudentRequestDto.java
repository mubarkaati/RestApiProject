package com.usman.studentData.model.dto.request;

import lombok.Data;

@Data
public class StudentRequestDto {
    private String studentName;
    private long phoneNumber;
    private String studentCity;
}