package com.springrest.LDManagement.controller;

import com.springrest.LDManagement.entities.Feedback;
import com.springrest.LDManagement.entities.Student;
import com.springrest.LDManagement.entities.Trainer;
import com.springrest.LDManagement.service.FeedbackRepositoryImplementation;
import com.springrest.LDManagement.service.StudentRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepositoryImplementation studentRepositoryImplementation;

    @Autowired
    FeedbackRepositoryImplementation feedbackRepositoryImplementation;

    @PostMapping("/giveFeedback")
    public ResponseEntity giveFeedback(@RequestBody Feedback feedback) {
        try {
            return new ResponseEntity(Optional.of(feedbackRepositoryImplementation.giveFeedback(feedback)).orElse(null), HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getStudent/{studentId}")
    public ResponseEntity getTrainer(@PathVariable String studentId) {
        try {
            Student student = studentRepositoryImplementation.getStudent(Long.parseLong(studentId));
            if (student != null) {
                return new ResponseEntity(student, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}