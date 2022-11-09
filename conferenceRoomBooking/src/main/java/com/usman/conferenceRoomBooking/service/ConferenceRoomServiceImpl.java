package com.usman.conferenceRoomBooking.service;

import com.usman.conferenceRoomBooking.entities.ConferenceRoom;
import com.usman.conferenceRoomBooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConferenceRoomServiceImpl implements ConferenceRoomService {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public ConferenceRoom addRoom(ConferenceRoom room) {
        return roomRepository.save(room);
    }
}