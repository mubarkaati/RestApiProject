package com.springrest.LDManagement.service;

import com.springrest.LDManagement.entities.Feedback;
import com.springrest.LDManagement.entities.Trainer;
import com.springrest.LDManagement.model.DTO.FeedbackDto;
import com.springrest.LDManagement.repository.FeedbackRepository;
import com.springrest.LDManagement.repository.StudentRepository;
import com.springrest.LDManagement.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackRepositoryImplementation {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    StudentRepository studentRepository;

    public List<Feedback> getAllFeedback() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        if (feedbacks != null) {
            for (Feedback feedback : feedbacks) {
                feedback.setTrainerName(trainerRepository.getByTrainerId(feedback.getTrainerId()).getTrainerName());
                feedback.setStudentName(studentRepository.getByStudentId(feedback.getStudentId()).getStudentName());
            }
            return feedbacks;
        }
        else {
            return null;
        }
    }

    public Feedback giveFeedback(Feedback feedback) {
        Long studentId = feedback.getStudentId();
        feedback.setTrainerId(studentRepository.findById(studentId).orElse(null).getStudentTrainer().getTrainerId());
        System.out.println(feedback.getTrainerId());
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getTrainerFeedback(Long trainerId) {
        List<Feedback> feedbacks = feedbackRepository.findByTrainerId(trainerId);
        if (feedbacks != null) {
            for (Feedback feedback : feedbacks) {
                feedback.setTrainerName(trainerRepository.getByTrainerId(feedback.getTrainerId()).getTrainerName());
                feedback.setStudentName(studentRepository.getByStudentId(feedback.getStudentId()).getStudentName());
            }
            return feedbacks;
        }
        else {
            return null;
        }
    }

    public float getTrainerFeedbackAverage(Long trainerId) {
        List<Feedback> feedbacks = feedbackRepository.findByTrainerId(trainerId);
        float averageRating = 0;
        float totalRating = 0, ratingCount = 0;
        if (feedbacks != null) {
            for (Feedback feedback : feedbacks) {
                feedback.setTrainerName(trainerRepository.getByTrainerId(feedback.getTrainerId()).getTrainerName());
                feedback.setStudentName(studentRepository.getByStudentId(feedback.getStudentId()).getStudentName());
                ratingCount++;
                totalRating += feedback.getFeedbackRating();
            }
            averageRating = totalRating/ratingCount;
            return averageRating;
        }
        else {
            return 0.0f;
        }
    }
}