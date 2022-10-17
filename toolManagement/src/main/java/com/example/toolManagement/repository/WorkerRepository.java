package com.example.toolManagement.repository;

import com.example.toolManagement.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query("SELECT w.workerId FROM Worker w WHERE w.workerUsername = ?1")
    Long findIdByUsername(String username);
}