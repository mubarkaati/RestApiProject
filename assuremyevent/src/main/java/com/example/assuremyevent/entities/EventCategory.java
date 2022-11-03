package com.example.assuremyevent.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class EventCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventCategoryId;
    @Column(nullable = false)
    private String eventCategoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventCategory")
    private List<Event> events;
}