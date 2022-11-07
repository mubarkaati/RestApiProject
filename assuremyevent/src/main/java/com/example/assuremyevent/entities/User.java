package com.example.assuremyevent.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    private String username;
    private String userEmail;
    private String userPassword;
    private String userRole;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Booking> bookings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Feedback> feedbacks;

    //for admin to manage event category
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
    private List<EventCategory> eventCategories;
}