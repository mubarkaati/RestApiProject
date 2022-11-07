package com.example.assuremyevent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {
    private int feedbackId;
    private float feedbackRating;
    private String feedbackComment;
    private int eventId;
    private int customerId;
}