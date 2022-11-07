package com.example.assuremyevent.model.dto.response;

import com.example.assuremyevent.entities.Event;
import com.example.assuremyevent.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponseDto {
    private int feedbackId;
    private float feedbackRating;
    private String feedbackComment;
    private String eventName;
}