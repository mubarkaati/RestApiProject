package com.springrest.LDManagement.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employment_type_details")
public class EmploymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long employmentTypeId;
    @Column(nullable = false)
    private float employmentTypeHours;
    @OneToMany(mappedBy = "employmentType", cascade = CascadeType.ALL)
    private List<Trainer> trainerEmploymentTypes = new ArrayList<>();
    @Column(nullable = false)
    private String employmentType;

    public Long getEmploymentTypeId() {
        return employmentTypeId;
    }

    public void setEmploymentTypeId(Long employmentTypeId) {
        this.employmentTypeId = employmentTypeId;
    }

    public float getEmploymentTypeHours() {
        return employmentTypeHours;
    }

    public void setEmploymentTypeHours(float employmentTypeHours) {
        this.employmentTypeHours = employmentTypeHours;
    }

    public List<Trainer> getTrainerEmploymentTypes() {
        return trainerEmploymentTypes;
    }

    public void setTrainerEmploymentTypes(List<Trainer> trainerEmploymentTypes) {
        this.trainerEmploymentTypes = trainerEmploymentTypes;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }
}
