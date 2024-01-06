package com.studentsinternship.demo.repository;

import com.studentsinternship.demo.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    Company findByName(String name);
}
