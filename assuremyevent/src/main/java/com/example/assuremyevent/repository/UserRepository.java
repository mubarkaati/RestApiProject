package com.example.assuremyevent.repository;

import com.example.assuremyevent.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserEmail(String userEmail);
}