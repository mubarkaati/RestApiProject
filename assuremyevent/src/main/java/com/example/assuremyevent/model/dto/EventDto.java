package com.example.assuremyevent.model.dto;

import lombok.Data;

@Data
public class EventDto {
    private int eventId;
    private String eventName;
    private String eventVenue;
    private String eventCity;
    private float eventBasePrice;
    private float pricePerPerson;
    private boolean isDeleted;
    private float averageFeedbackRating;
    private int eventCategoryId;
    private int eventOrganizerId;
}
