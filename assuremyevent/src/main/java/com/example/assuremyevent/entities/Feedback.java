package com.example.assuremyevent.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int feedbackId;
    @Column(nullable = false)
    private float feedbackRating;
    @Lob
    @Column(nullable = false)
    private String feedbackComment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "event")
    @JsonIgnore
    private Event event;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer")
    @JsonIgnore
    private User user;
}