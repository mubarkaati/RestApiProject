package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Subject {
    @Id
    private int subjectId;
    private String subjectName;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    public Subject(int subjectId, String subjectName, List<Student> students) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.students = students;
    }

    public Subject() {
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", students=" + students +
                '}';
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
