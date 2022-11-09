package com.usman.conferenceRoomBooking.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ConferenceRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    private String roomName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<RoomBooking> roomBookingList;
}