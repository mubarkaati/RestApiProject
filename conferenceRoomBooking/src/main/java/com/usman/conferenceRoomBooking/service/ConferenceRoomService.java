package com.usman.conferenceRoomBooking.service;

import com.usman.conferenceRoomBooking.entities.ConferenceRoom;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface ConferenceRoomService {
    ConferenceRoom addRoom(ConferenceRoom room);
}
