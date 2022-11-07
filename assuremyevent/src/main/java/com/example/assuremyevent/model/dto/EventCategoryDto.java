package com.example.assuremyevent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCategoryDto {
    private int eventCategoryId;
    private String eventCategoryName;
}