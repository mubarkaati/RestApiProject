package com.coditas.StudentManagementSystem.services;

import com.coditas.StudentManagementSystem.entities.Student;
import com.coditas.StudentManagementSystem.utils.GetSessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDaoImplementation implements StudentDao {
    @Override
    public void registerStudent(Student student) {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteStudent(int enrollmentNo) {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.get(Student.class, enrollmentNo));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Student> fetchAllStudent() {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        List<Student> students = session.createQuery("from Student").list();
        session.close();
        return students;
    }

    @Override
    public void updateStudent(Student student) {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(student);
        session.getTransaction().commit();
        session.close();
    }
}