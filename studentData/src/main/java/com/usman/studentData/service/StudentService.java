package com.usman.studentData.service;

import com.usman.studentData.entities.Student;
import com.usman.studentData.model.dto.request.StudentRequestDto;
import com.usman.studentData.model.dto.response.StudentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface StudentService {
    StudentResponseDto addStudent(StudentRequestDto requestDto);
    StudentResponseDto updateStudent(StudentRequestDto requestDto, int studentId);
    List<StudentResponseDto> getAllStudents();
    void deleteStudent(int StudentId);

    void assignSubject(int subjectId, int studentId);

    List<Student> getAllStudentsWithSubject();

    Student getByNameAndMobile(String name, long mobileNumber);
}