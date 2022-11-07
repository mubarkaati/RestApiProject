package com.example.assuremyevent.model.dto.request;

import lombok.Data;

@Data
public class EventRequestDto {
    private int eventId;
    private String eventName;
    private String eventVenue;
    private String eventCity;
    private float eventBasePrice;
    private float pricePerPerson;
    private int eventOrganizerId;
    private int eventCategoryId;
}