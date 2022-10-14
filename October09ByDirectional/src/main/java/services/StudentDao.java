package services;

import model.Student;
import model.Subject;

public interface StudentDao {
    String registerStudent(Student student);
    String registerSubject(Subject subject);
    String assignSubject(int studentId, int subjectId);
    void displayStudent(int studentId);
    void displaySubject(int subjectId);
}
