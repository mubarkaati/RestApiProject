package com.usman.studentData.repository;

import com.usman.studentData.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByStudentNameOrPhoneNumber(String name, long mobileNumber);
}