package com.usman.conferenceRoomBooking.repository;

import com.usman.conferenceRoomBooking.entities.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<ConferenceRoom, Integer> {
}