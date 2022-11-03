package com.springrest.LDManagement.service;

import com.springrest.LDManagement.entities.Domain;
import com.springrest.LDManagement.entities.Student;
import com.springrest.LDManagement.entities.User;
import com.springrest.LDManagement.model.DTO.StudentDto;
import com.springrest.LDManagement.repository.DomainRepository;
import com.springrest.LDManagement.repository.StudentRepository;
import com.springrest.LDManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentRepositoryImplementation {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DomainRepository domainRepository;

    public Student addStudent(StudentDto studentDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(studentDto.getStudentPassword());
        User user = new User();
        user.setPassword(encryptedPassword);
        user.setEmail(studentDto.getStudentEmail());
        user.setRole("STUDENT");
        userRepository.save(user);

        Student student = new Student();
        System.out.println(user);
        student.setStudentEmail(studentDto.getStudentEmail());
        student.setStudentName(studentDto.getStudentName());
        Domain domain = domainRepository.findById(studentDto.getDomainId()).orElse(null);
        System.out.println(domain);
        System.out.println(studentDto);
        student.setStudentDomain(domain);
        student.setStudentTrainer(domain.getTrainer());
        System.out.println(student);
        return studentRepository.save(student);
    }

    public Student deleteStudent(long studentId) {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);
        if (existingStudent != null) {
            existingStudent.setDeleted(true);
            User user = userRepository.findByEmail(existingStudent.getStudentEmail());
            userRepository.deleteById(user.getId());
            return studentRepository.save(existingStudent);
        }
        else {
            return null;
        }
    }

    public List<Student> getStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().filter(trainer -> !trainer.isDeleted()).collect(Collectors.toList());
    }

    public Student updateStudent(StudentDto studentDto) {
        Student existingStudent = studentRepository.findById(studentDto.getStudentId()).orElse(null);
        if (existingStudent != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encryptedPassword = passwordEncoder.encode(studentDto.getStudentPassword());
            User existingUser = userRepository.findByEmail(existingStudent.getStudentEmail());
            existingUser.setPassword(encryptedPassword);
            existingUser.setEmail(studentDto.getStudentEmail());
            existingUser.setRole("STUDENT");
            userRepository.save(existingUser);

            existingStudent.setStudentEmail(studentDto.getStudentEmail());
            existingStudent.setStudentName(studentDto.getStudentName());
            Domain domain = domainRepository.findById(studentDto.getDomainId()).orElse(null);
            existingStudent.setStudentDomain(domain);
            existingStudent.setStudentTrainer(domain.getTrainer());
            return studentRepository.save(existingStudent);
        }
        else {
            return null;
        }
    }

    public Student getStudent(long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }
}