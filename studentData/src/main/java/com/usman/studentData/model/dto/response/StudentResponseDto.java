package com.usman.studentData.model.dto.response;

import lombok.Data;

@Data
public class StudentResponseDto {
    private int studentId;
    private String studentName;
    private long phoneNumber;
}