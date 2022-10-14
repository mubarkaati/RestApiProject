package com.example.toolManagement.repository;

import com.example.toolManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkeRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
