package com.example.assuremyevent.repository;

import com.example.assuremyevent.entities.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Integer> {
}