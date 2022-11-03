package com.springrest.LDManagement.repository;

import com.springrest.LDManagement.entities.Student;
import com.springrest.LDManagement.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findIdByStudentEmail(String email);

    Student getByStudentId(Long studentId);
}
