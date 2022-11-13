package com.usman.studentData.controller;

import com.usman.studentData.service.StudentService;
import com.usman.studentData.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    StudentService studentService;

    @Autowired
    SubjectService subjectService;

    @PostMapping("/assign/{subjectId}/{studentId}")
    public ResponseEntity assignSubject(@PathVariable int subjectId, @PathVariable int studentId) {
        studentService.assignSubject(subjectId, studentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity getAllStudents() {
        return new ResponseEntity(studentService.getAllStudentsWithSubject(), HttpStatus.OK);
    }
}