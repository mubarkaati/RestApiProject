package com.example.assuremyevent.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String customerEmail;
    @Column(nullable = false)
    private String customerPassword;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Booking> bookings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Feedback> feedbacks;
}