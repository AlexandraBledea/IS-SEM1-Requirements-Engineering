package com.studentsinternship.demo.repository;

import com.studentsinternship.demo.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipRepository extends JpaRepository<Internship,Long> {
}
