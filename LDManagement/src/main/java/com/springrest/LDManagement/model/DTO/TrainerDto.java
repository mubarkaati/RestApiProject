package com.springrest.LDManagement.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto {
    private Long trainerId;
    private String trainerName;
    private String trainerEmail;
    private float trainerSalary;
    private String trainerPassword;
    private Long employmentTypeId;
    private Long domainId;
}
