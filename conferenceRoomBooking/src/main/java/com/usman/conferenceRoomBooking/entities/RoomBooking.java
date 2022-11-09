package com.usman.conferenceRoomBooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
public class RoomBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;
    private long startDateAndTime;
    private long endDateAndTime;
    @ColumnDefault("false")
    private boolean isCancelled;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee")
    @JsonIgnore
    private Employee bookingEmployee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conference_room")
    @JsonIgnore
    private ConferenceRoom room;
}