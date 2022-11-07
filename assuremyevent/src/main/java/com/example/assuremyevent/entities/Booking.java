package com.example.assuremyevent.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;
    @Column(nullable = false)
    private float totalPrice;
    @Column(nullable = false)
    private int numberOfGuest;
    @Column(nullable = false)
    private Time startTime;
    @Column(nullable = false)
    private Time endTime;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    @Column(nullable = false)
    private String bookingStatus;
    @ColumnDefault("0")
    private boolean isDeleted;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event")
    @JsonIgnore
    private Event event;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer")
    @JsonIgnore
    private User user;
}