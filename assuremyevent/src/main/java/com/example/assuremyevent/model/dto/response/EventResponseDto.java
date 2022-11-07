package com.example.assuremyevent.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto {
    private int eventId;
    private String eventName;
    private String eventVenue;
    private String eventCity;
    private float eventBasePrice;
    private float pricePerPerson;
    private float averageFeedbackRating;
}