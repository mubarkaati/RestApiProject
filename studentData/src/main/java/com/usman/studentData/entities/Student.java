package com.usman.studentData.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;

    @Column(nullable = false)
    private String studentName;

    @Column(unique = true)
    private long phoneNumber;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "students")
    @JsonIgnore
    private List<Subject> subjects;
}