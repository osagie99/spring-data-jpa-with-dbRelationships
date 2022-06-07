package com.surgeindustries.spring.data.jpa.repository;

import com.surgeindustries.spring.data.jpa.entity.Course;
import com.surgeindustries.spring.data.jpa.entity.Student;
import com.surgeindustries.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Test
    public void printCourses() {
        List<Course> courseList = courseRepository.findAll();
        System.out.println("courseList = " + courseList);
    }


    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Anthony")
                .lastName("Masha")
                .build();

        Course course = Course.builder()
                .title("Physics")
                .credit(2)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void printAllCoursesWithPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords).getContent();
        long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();
        int totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();
        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);

    }

    @Test
    public void findAllCoursesWithSorting() {
        Pageable sortByTitle = PageRequest.of(0, 2,
                Sort.by("title").ascending());
        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        System.out.println("courses = " + courses);
    }
    
    @Test
    public void findByTitleAndContaining() {
        Pageable sort = PageRequest.of(0,3);
        List<Course> courses = courseRepository.findByTitleContaining("D", sort).getContent();
        System.out.println("courses = " + courses);
    }


    @Test
    public void saveCourseWithStudentAndTeacher() {
//        Teacher teacher = teacherRepository.findById(2L).get();
        Course course = courseRepository.findById(7L).get();
        Student student = Student
                .builder()
                .emailId("test05@gmail")
                .firstName("Aliu")
                .lastName("Soft")
                .build();
//        Teacher teacher = Teacher.builder()
//                .firstName("MR. james")
//                .lastName("Elegbon")
//                .build();
//        Course course = Course.builder()
//                .title("Science")
//                .credit(10)
//                .teacher(teacher)
////                .courseMaterial()
//                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }


}