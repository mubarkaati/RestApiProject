package com.usman.studentData.controller;

import com.usman.studentData.model.dto.request.StudentRequestDto;
import com.usman.studentData.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequestDto requestDto) {
        return new ResponseEntity(studentService.addStudent(requestDto), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{studentId}")
    public ResponseEntity updateStudent(@RequestBody StudentRequestDto requestDto, @PathVariable int studentId) {
        return new ResponseEntity(studentService.updateStudent(requestDto, studentId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable int studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllStudent() {
        return new ResponseEntity(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/getByNameAndMobile/{name}/{mobileNumber}")
    public ResponseEntity getByNameAndMobile(@PathVariable String name, @PathVariable long mobileNumber) {
        return new ResponseEntity(studentService.getByNameAndMobile(name, mobileNumber), HttpStatus.OK);
    }
}