package com.usman.studentData.service;

import com.usman.studentData.entities.Student;
import com.usman.studentData.entities.Subject;
import com.usman.studentData.exception.NoStudentExistException;
import com.usman.studentData.exception.StudentNotFoundException;
import com.usman.studentData.exception.SubjectNotFoundException;
import com.usman.studentData.model.dto.request.StudentRequestDto;
import com.usman.studentData.model.dto.response.StudentResponseDto;
import com.usman.studentData.repository.StudentRepository;
import com.usman.studentData.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentResponseDto addStudent(StudentRequestDto requestDto) {
        return this.modelMapper.map(studentRepository.save
                (this.modelMapper.map(requestDto, Student.class)), StudentResponseDto.class);
    }

    @Override
    public StudentResponseDto updateStudent(StudentRequestDto requestDto, int studentId) {
        if (studentRepository.findById(studentId).isPresent()) {
            Student student = this.modelMapper.map(requestDto, Student.class);
            student.setStudentId(studentId);
            return (this.modelMapper.map(studentRepository.save(student), StudentResponseDto.class));
        } else {
            throw new StudentNotFoundException();
        }
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        if (studentList.size() > 0) {
            return studentList.stream()
                    .map(student -> this.modelMapper.map(student, StudentResponseDto.class))
                    .collect(Collectors.toList());
        } else {
            throw new NoStudentExistException();
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        if (studentRepository.findById(studentId).isPresent()) {
            studentRepository.deleteById(studentId);
        } else {
            throw new StudentNotFoundException();
        }
    }

    @Override
    public void assignSubject(int subjectId, int studentId) {
        if (studentRepository.findById(studentId).isPresent()) {
            if (subjectRepository.findById(subjectId).isPresent()) {
                Student student = studentRepository.findById(studentId).orElse(null);
                List<Subject> subjectList;
                subjectList = student.getSubjects();
                if (subjectList.size() == 0) {
                    subjectList = new ArrayList<>();
                }
                subjectList.add(subjectRepository.findById(subjectId).orElse(null));
                student.setSubjects(subjectList);
                studentRepository.save(student);
            } else {
                throw new SubjectNotFoundException();
            }
        } else {
            throw new StudentNotFoundException();
        }
    }

    @Override
    public List<Student> getAllStudentsWithSubject() {
        return studentRepository.findAll();
    }

    @Override
    public Student getByNameAndMobile(String name, long mobileNumber) {
        Student student = studentRepository.findByStudentNameOrPhoneNumber(name, mobileNumber);
        return student;
    }
}