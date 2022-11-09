package com.usman.conferenceRoomBooking.repository;

import com.usman.conferenceRoomBooking.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}