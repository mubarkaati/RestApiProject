package controller;

import model.Student;
import model.Subject;
import services.StudentDao;
import services.StudentDaoImplementation;
import utils.GetSessionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StudentMenu {
    public void menuOptions() throws IOException {
        GetSessionFactory.getSessionFactory();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StudentDao studentDao = new StudentDaoImplementation();
        do {
            System.out.println("*******************************************");
            System.out.println("Enter your choice");
            System.out.println("0. Exit");
            System.out.println("1. Insert Student");
            System.out.println("2. Insert Subject");
            System.out.println("3. Assign Subject");
            System.out.println("4. Display Student");
            System.out.println("5. Display Subject");
            System.out.println("*******************************************");
            switch (Integer.parseInt(bufferedReader.readLine())) {
                case 0:
                    System.exit(0);
                    break;

                case 1:
                    System.out.println("Enter Student Id and Student Name");
                    List<Subject> subjects = null;
                    System.out.println(studentDao.registerStudent(
                            new Student(Integer.parseInt(bufferedReader.readLine()),
                            bufferedReader.readLine(),
                            null)
                    ));
                    break;

                case 2:
                    System.out.println("Enter Subject Id and Subject Name");
                    System.out.println(studentDao.registerSubject(new Subject(Integer.parseInt(bufferedReader.readLine()),
                            bufferedReader.readLine(),
                            null)
                    ));
                    break;

                case 3:
                    System.out.println("Enter Student Id and Subject Id");
                    System.out.println(studentDao.assignSubject(Integer.parseInt(bufferedReader.readLine()), Integer.parseInt(bufferedReader.readLine())));
                    break;

                case 4:
                    System.out.println("Enter Student Id");
                    studentDao.displayStudent(Integer.parseInt(bufferedReader.readLine()));
                    break;

                case 5:
                    System.out.println("Enter Subject Id");
                    studentDao.displaySubject(Integer.parseInt(bufferedReader.readLine()));
                    break;

                default:
                    System.out.println("Invalid Choice try again");
                    break;
            }
        }
        while (true);
    }
}