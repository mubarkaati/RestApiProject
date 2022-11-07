package com.example.assuremyevent.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class EventCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventCategoryId;
    @Column(nullable = false)
    private String eventCategoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventCategory")
    private List<Event> events;

    //for admin to manage eventCategory
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User admin;
}