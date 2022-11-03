package com.springrest.LDManagement.repository;

import com.springrest.LDManagement.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByTrainerId(Long trainerId);
}
