package com.studentsinternship.demo.repository;

import com.studentsinternship.demo.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Long> {
}
