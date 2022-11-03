package com.springrest.LDManagement.entities;

import javax.persistence.*;

@Entity
@Table(name = "feedback_details")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long feedbackId;
    @Column(nullable = false)
    private Long studentId;
    @Transient
    private String studentName;
    @Column(nullable = false)
    private Long trainerId;
    @Transient
    private String trainerName;
    @Column(nullable = false)
    private float feedbackRating;
    @Column(nullable = false)
    private String feedbackComment;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public float getFeedbackRating() {
        return feedbackRating;
    }

    public void setFeedbackRating(float feedbackRating) {
        this.feedbackRating = feedbackRating;
    }

    public String getFeedbackComment() {
        return feedbackComment;
    }

    public void setFeedbackComment(String feedbackComment) {
        this.feedbackComment = feedbackComment;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }
}