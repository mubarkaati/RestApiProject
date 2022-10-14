package com.coditas.StudentManagementSystem.services;

import com.coditas.StudentManagementSystem.entities.Student;

import java.util.List;

public interface StudentDao {
    void registerStudent(Student student);
    void deleteStudent(int enrollmentNo);
    List<Student> fetchAllStudent();
    void updateStudent(Student student);
}
