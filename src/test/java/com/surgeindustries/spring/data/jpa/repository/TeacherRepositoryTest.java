package com.surgeindustries.spring.data.jpa.repository;

import com.surgeindustries.spring.data.jpa.entity.Course;
import com.surgeindustries.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {

        Course course = Course.builder()
                .title("DSA")
                .credit(3)
                .build();

        Course course2 = Course.builder()
                .title("Java")
                .credit(3)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Adeniran")
                .lastName("Wendy")
//                .courses(List.of(course))
                .build();

        teacherRepository.save(teacher);
    }

}