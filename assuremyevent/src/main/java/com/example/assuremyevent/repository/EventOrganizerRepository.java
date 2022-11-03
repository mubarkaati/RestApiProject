package com.example.assuremyevent.repository;

import com.example.assuremyevent.entities.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventOrganizerRepository extends JpaRepository<EventOrganizer, Integer> {

}