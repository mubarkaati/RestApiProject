package com.springrest.LDManagement.controller;

import com.springrest.LDManagement.entities.*;
import com.springrest.LDManagement.model.DTO.StudentDto;
import com.springrest.LDManagement.model.DTO.TrainerDto;
import com.springrest.LDManagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    TrainerRepositoryImplementation trainerRepositoryImplementation;

    @PostMapping("/addTrainer")
    public ResponseEntity addTrainer(@RequestBody TrainerDto trainerDto) {
        try {
            return new ResponseEntity(Optional.of(trainerRepositoryImplementation.addTrainer(trainerDto)), HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTrainers")
    public ResponseEntity getTrainers() {
        try {
            List<Trainer> trainers = trainerRepositoryImplementation.getTrainers();
            if (trainers.size() > 0) {
                return new ResponseEntity(trainers, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateTrainer")
    public ResponseEntity updateTrainer(@RequestBody TrainerDto trainerDto) {
        try {
            return new ResponseEntity(Optional.of(trainerRepositoryImplementation.updateTrainer(trainerDto)), HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteTrainer/{trainerId}")
    public ResponseEntity deleteTrainer(@PathVariable String trainerId) {
        try {
            Trainer trainer = trainerRepositoryImplementation.deleteTrainer(Long.parseLong(trainerId));
            if (trainer != null) {
                return new ResponseEntity(trainer, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //*********************************************************************//
    //Student Operations

    @Autowired
    StudentRepositoryImplementation studentRepositoryImplementation;

    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestBody StudentDto studentDto) {
        try {
            return new ResponseEntity(Optional.of(studentRepositoryImplementation.addStudent(studentDto)), HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable String studentId) {
        try {
            Student student = studentRepositoryImplementation.deleteStudent(Long.parseLong(studentId));
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

    @GetMapping("/getStudents")
    public ResponseEntity getStudents() {
        try {
            List<Student> students = studentRepositoryImplementation.getStudents();
            if (students.size() > 0) {
                return new ResponseEntity(students, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateStudent")
    public ResponseEntity updateStudent(@RequestBody StudentDto studentDto) {
        try {
            return new ResponseEntity(studentRepositoryImplementation.updateStudent(studentDto), HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //*********************************************************************//
    //Feedback

    @Autowired
    FeedbackRepositoryImplementation feedbackRepositoryImplementation;

    @GetMapping("/getFeedback")
    public ResponseEntity getFeedback() {
        try {
            List<Feedback> feedbacks = feedbackRepositoryImplementation.getAllFeedback();
            if (feedbacks.size() > 0) {
                return new ResponseEntity(feedbacks, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //**************************************************************************//
    //Get Domains

    @Autowired
    DomainRepositoryImplementation domainRepositoryImplementation;

    @GetMapping("/getDomains")
    public ResponseEntity getDomains() {
        try {
            List<Domain> domains = domainRepositoryImplementation.getDomains();
            if (domains.size() > 0) {
                return new ResponseEntity(domains, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllDomains")
    public ResponseEntity getAllDomains() {
        try {
            List<Domain> domains = domainRepositoryImplementation.getAllDomains();
            if (domains.size() > 0) {
                return new ResponseEntity(domains, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAssignedDomains")
    public ResponseEntity getAssignedDomains() {
        try {
            List<Domain> domains = domainRepositoryImplementation.getAssignedDomains();
            if (domains.size() > 0) {
                return new ResponseEntity(domains, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //**************************************************************************//
    //Get Employments

    @Autowired
    EmploymentTypeRepositoryImplementation employmentTypeRepositoryImplementation;

    @GetMapping("/getEmploymentType")
    public ResponseEntity getEmploymentType() {
        try {
            List<EmploymentType> employmentTypes = employmentTypeRepositoryImplementation.getEmploymentType();
            if (employmentTypes.size() > 0) {
                return new ResponseEntity(employmentTypes, HttpStatus.OK);
            }
            else {
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //**************************************************************************//
    //Get Emails

    @Autowired
    UserRepositoryImplementation userRepositoryImplementation;

    @GetMapping("/getEmails")
    public ResponseEntity getEmails() {
        try {
            List<String> emails = userRepositoryImplementation.getEmails();
            if (emails != null) {
                return new ResponseEntity(Optional.of(emails), HttpStatus.OK);
            }
            else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //**************************************************************************//
    //Get FeedBack

    @GetMapping("/getFeedback/{trainerId}")
    public ResponseEntity getFeedback(@PathVariable String trainerId) {
        try {
            List<Feedback> trainers = feedbackRepositoryImplementation.getTrainerFeedback(Long.parseLong(trainerId));
            if (trainers != null) {
                return new ResponseEntity(trainers, HttpStatus.OK);
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