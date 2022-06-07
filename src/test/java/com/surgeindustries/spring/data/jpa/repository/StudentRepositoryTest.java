package com.surgeindustries.spring.data.jpa.repository;

import com.surgeindustries.spring.data.jpa.entity.Course;
import com.surgeindustries.spring.data.jpa.entity.Guardian;
import com.surgeindustries.spring.data.jpa.entity.Student;
import com.surgeindustries.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("osagieabel99@icloud.com")
                .firstName("Osagie2")
                .lastName("Abel2")
//                .guardianName("Kenny")
//                .guardianEmail("kenny@gmail.com")
//                .guardianMobile("08023891717")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("Omozojie")
                .email("abel@gmail.com")
                .mobile("0814305283")
                .build();

        Student student = Student.builder()
                .firstName("James")
                .lastName("Dean")
                .emailId("jamesdean@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void fetchAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }
    
    @Test 
    public void findByFirstName() {
        List<Student> student = studentRepository.findByFirstName("Hello");
        System.out.println("student = " + student);
    }

    @Test
    public void fetchByEmailAddress() {
        Student student = studentRepository.fetchStudentByEmailAddressNative("osagieabel@icloud.com");
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentNameByEmailIdTest() {
        studentRepository.updateStudentNameByEmailId(
                "James Dean",
                "jamesdean@gmail.com"
                );
    }


}