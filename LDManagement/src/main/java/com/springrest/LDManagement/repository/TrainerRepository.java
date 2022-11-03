package com.springrest.LDManagement.repository;

import com.springrest.LDManagement.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findIdByTrainerEmail(String email);

    Trainer getByTrainerId(Long trainerId);
}
