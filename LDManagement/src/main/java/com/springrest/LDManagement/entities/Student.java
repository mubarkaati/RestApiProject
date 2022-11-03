package com.springrest.LDManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student_details")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long studentId;
    @Column(nullable = false)
    private String studentName;
    @Column(nullable = false, unique = true)
    private String studentEmail;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_domain", nullable = false)
    private Domain studentDomain;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_trainer", nullable = false)
    @JsonIgnore
    private Trainer studentTrainer;
    @ColumnDefault(value = "false")
    private boolean isDeleted;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Domain getStudentDomain() {
        return studentDomain;
    }

    public void setStudentDomain(Domain studentDomain) {
        this.studentDomain = studentDomain;
    }

    public Trainer getStudentTrainer() {
        return studentTrainer;
    }

    public void setStudentTrainer(Trainer studentTrainer) {
        this.studentTrainer = studentTrainer;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
