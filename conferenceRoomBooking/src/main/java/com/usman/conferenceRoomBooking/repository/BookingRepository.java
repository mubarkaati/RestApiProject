package com.usman.conferenceRoomBooking.repository;

import com.usman.conferenceRoomBooking.entities.ConferenceRoom;
import com.usman.conferenceRoomBooking.entities.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<RoomBooking, Integer> {
    List<RoomBooking> findByRoom(ConferenceRoom conferenceRoom);
}