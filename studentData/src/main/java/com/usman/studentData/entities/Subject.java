package com.usman.studentData.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subjectId;

    private String subjectName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "students_subject")
    @JsonIgnore
    private List<Student> students;
}