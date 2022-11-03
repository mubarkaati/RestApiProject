package com.springrest.LDManagement.controller;


import com.springrest.LDManagement.entities.Feedback;
import com.springrest.LDManagement.entities.Trainer;
import com.springrest.LDManagement.service.FeedbackRepositoryImplementation;
import com.springrest.LDManagement.service.TrainerRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
@CrossOrigin("*")
public class TrainerController {

    @Autowired
    TrainerRepositoryImplementation trainerRepositoryImplementation;

    @GetMapping("/getTrainer/{trainerId}")
    public ResponseEntity getTrainer(@PathVariable String trainerId) {
        try {
            Trainer trainer = trainerRepositoryImplementation.getTrainer(Long.parseLong(trainerId));
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

    @Autowired
    FeedbackRepositoryImplementation feedbackRepositoryImplementation;

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

    @GetMapping("/getFeedbackRating/{trainerId}")
    public ResponseEntity getFeedbackRating(@PathVariable String trainerId) {
        try {
            return new ResponseEntity(feedbackRepositoryImplementation.getTrainerFeedbackAverage(Long.parseLong(trainerId)), HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}