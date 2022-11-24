package com.usman.loginPage.repository;

import com.usman.loginPage.entities.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Integer> {
    UserTable findByUserEmailAndPassword(String userEmail, String password);
}