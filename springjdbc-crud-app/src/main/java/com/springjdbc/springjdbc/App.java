package com.springjdbc.springjdbc;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springjdbc.springjdbc.dao.StudentDAO;
import com.springjdbc.springjdbc.model.Student;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("com/springjdbc/springjdbc/config.xml");
        StudentDAO studentDao = (StudentDAO) context.getBean("studentDao");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printWelcomeMessage();
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Get Single Student");
            System.out.println("5. Get All Students");
            System.out.println("6. Exit");
            System.out.println("Enter your choice (1-6): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(studentDao, scanner);
                    break;

                case 2:
                    deleteStudent(studentDao, scanner);
                    break;

                case 3:
                    updateStudent(studentDao, scanner);
                    break;

                case 4:
                    getSingleStudent(studentDao, scanner);
                    break;

                case 5:
                    getAllStudents(studentDao);
                    break;

                case 6:
                    System.out.println("Exiting Student Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("********************************************");
        System.out.println("*     WELCOME TO STUDENT MANAGEMENT SYSTEM    *");
        System.out.println("********************************************");
    }

    private static void addStudent(StudentDAO studentDao, Scanner scanner) {
        System.out.println("Enter student details:");
        System.out.println("Student ID:");
        int studentId = scanner.nextInt();
        System.out.println("Student Name:");
        String studentName = scanner.next();
        System.out.println("Student Address:");
        String studentAddress = scanner.next();

        Student newStudent = new Student(studentId, studentName, studentAddress);
        studentDao.addStudent(newStudent);
        System.out.println("Student added successfully!");
    }

    private static void deleteStudent(StudentDAO studentDao, Scanner scanner) {
        System.out.println("Enter student ID to delete:");
        int deleteId = scanner.nextInt();
        studentDao.deleteStudent(deleteId);
        System.out.println("Student deleted successfully!");
    }

    private static void updateStudent(StudentDAO studentDao, Scanner scanner) {
        System.out.println("Enter student ID to update:");
        int updateId = scanner.nextInt();
        Student existingStudent = studentDao.getStudent(updateId);

        if (existingStudent != null) {
            System.out.println("Enter updated student details:");
            System.out.println("Student Name:");
            String updatedName = scanner.next();
            System.out.println("Student Address:");
            String updatedAddress = scanner.next();

            existingStudent.setstudentName(updatedName);
            existingStudent.setstudentCity(updatedAddress);

            studentDao.updateStudent(existingStudent);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student with ID " + updateId + " not found.");
        }
    }

    private static void getSingleStudent(StudentDAO studentDao, Scanner scanner) {
        System.out.println("Enter student ID to retrieve:");
        int getStudentId = scanner.nextInt();
        Student retrievedStudent = studentDao.getStudent(getStudentId);
        System.out.println("Retrieved Student: " + retrievedStudent);
    }

    private static void getAllStudents(StudentDAO studentDao) {
        List<Student> allStudents = studentDao.getAllStudents();
        System.out.println("All Students:");
        for (Student s : allStudents) {
            System.out.println(s);
        }
    }
}
