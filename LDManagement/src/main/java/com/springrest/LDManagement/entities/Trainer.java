package com.springrest.LDManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainer_details")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long trainerId;
    @Column(nullable = false)
    private String trainerName;
    @Column(nullable = false, unique = true)
    private String trainerEmail;
    @Column(nullable = false)
    private float trainerSalary;
    @OneToMany(mappedBy = "studentTrainer", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_employment_type")
    @JsonIgnore
    private EmploymentType employmentType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_domain")
    private Domain domain;
    @ColumnDefault(value = "false")
    private boolean isDeleted;

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getTrainerEmail() {
        return trainerEmail;
    }

    public void setTrainerEmail(String trainerEmail) {
        this.trainerEmail = trainerEmail;
    }

    public float getTrainerSalary() {
        return trainerSalary;
    }

    public void setTrainerSalary(float trainerSalary) {
        this.trainerSalary = trainerSalary;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}