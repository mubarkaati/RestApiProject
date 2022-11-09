package com.usman.conferenceRoomBooking.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    private int employeeId;
    private String employeeName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingEmployee")
    private List<RoomBooking> roomBookingList;
}