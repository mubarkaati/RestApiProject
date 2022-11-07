package com.example.assuremyevent.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventId;
    @Column(nullable = false)
    private String eventName;
    @Column(nullable = false)
    private String eventVenue;
    @Column(nullable = false)
    private String eventCity;
    @Column(nullable = false)
    private float eventBasePrice;
    @Column(nullable = false)
    private float pricePerPerson;
    @Column(nullable = false)
    @ColumnDefault("0")
    private boolean isDeleted;
    @Column(nullable = false)
    @ColumnDefault("0")
    private float averageFeedbackRating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_organizer")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_category")
    @JsonIgnore
    private EventCategory eventCategory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<Booking> bookings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<Feedback> feedbacks;
}