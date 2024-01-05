package com.studentsinternship.demo.repository;

import com.studentsinternship.demo.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterRepository extends JpaRepository<Recruiter,Long> {
}
