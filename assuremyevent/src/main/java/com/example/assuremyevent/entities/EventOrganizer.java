package com.example.assuremyevent.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class EventOrganizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventOrganizerId;
    @Column(nullable = false)
    private String organizerName;
    @Column(nullable = false)
    private String organizerEmail;
    @Column(nullable = false)
    private String organizerPassword;
    @Column(nullable = false)
    private boolean isDeleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventOrganizer")
    private List<Event> events;
}