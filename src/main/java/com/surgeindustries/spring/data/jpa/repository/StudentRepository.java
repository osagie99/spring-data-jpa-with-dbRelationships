package com.surgeindustries.spring.data.jpa.repository;

import com.surgeindustries.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstName(String firstName);

    @Query(
            value = "SELECT * FROM tbl_student WHERE student_email = :emailId",
            nativeQuery = true
    )
    Student fetchStudentByEmailAddressNative(@Param("emailId") String emailId);


    @Transactional
    @Modifying
    @Query(
            value = "update tbl_student set first_name = :firstName where student_email = :emailId",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(@Param("firstName") String firstName,@Param("emailId") String emailId);

}
