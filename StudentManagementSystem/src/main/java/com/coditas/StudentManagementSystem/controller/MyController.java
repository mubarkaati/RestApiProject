package com.coditas.StudentManagementSystem.controller;

import com.coditas.StudentManagementSystem.entities.Student;
import com.coditas.StudentManagementSystem.services.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {
    @Autowired
    private StudentDao studentDao;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/JSON")
    void registerStudent(@RequestBody Student student) {
        System.out.println(student);
        studentDao.registerStudent(student);
    }

    @GetMapping("/students")
    List<Student> fetchAllStudent() {
        return studentDao.fetchAllStudent();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/JSON")
    void updateStudent(@RequestBody Student student) {
        studentDao.updateStudent(student);
    }

    @DeleteMapping("/delete/{enrollmentId}")
    void deleteStudent(@PathVariable int enrollmentId) {
        studentDao.deleteStudent(enrollmentId);
    }
}