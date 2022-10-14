package services;

import model.Student;
import model.Subject;
import org.hibernate.Session;
import utils.GetSessionFactory;

import java.util.List;

public class StudentDaoImplementation implements StudentDao {
    @Override
    public String registerStudent(Student student) {
        try {
            Session session = GetSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            session.close();
            return "Student Registered Successfully!!";
        }
        catch (Exception e) {
            return "Cannot Register Student";
        }
    }

    @Override
    public String registerSubject(Subject subject) {
        try {
            Session session = GetSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(subject);
            session.getTransaction().commit();
            session.close();
            return "Subject Registered Successfully!!";
        }
        catch (Exception e) {
            return "Cannot Register Subject";
        }
    }

    @Override
    public String assignSubject(int studentId, int subjectId) {
        try {
            Session session = GetSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();

            Student student = session.get(Student.class, studentId);
            Subject subject = session.get(Subject.class, subjectId);

            List<Subject> subjects = student.getSubjects();
            subjects.add(subject);
            student.setSubjects(subjects);

            session.saveOrUpdate(student);
            session.getTransaction().commit();
            session.close();
            return "Subject Assigned Successfully!!";
        }
        catch (Exception e) {
            return "Cannot Assigned Subject";
        }
    }

    @Override
    public void displayStudent(int studentId) {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        Student student = session.get(Student.class, studentId);
        System.out.println(student);
        student.getSubjects().stream().forEach(System.out::println);
        session.close();
    }

    @Override
    public void displaySubject(int subjectId) {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        Subject subject = session.get(Subject.class, subjectId);
        System.out.println(subject);
        subject.getStudents().stream().forEach(System.out::println);
        session.close();
    }
}