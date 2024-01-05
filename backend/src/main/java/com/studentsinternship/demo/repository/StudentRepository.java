package com.studentsinternship.demo.repository;

import com.studentsinternship.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
