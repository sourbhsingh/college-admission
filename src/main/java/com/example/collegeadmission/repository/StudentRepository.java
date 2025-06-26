package com.example.collegeadmission.repository;

import com.example.collegeadmission.helper.Status;
import com.example.collegeadmission.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface StudentRepository extends JpaRepository<Student, Integer> {



    List<Student> findAllByCourse_id(Integer courseId);

    List<Student> findAllByStatus(Status status);
}
