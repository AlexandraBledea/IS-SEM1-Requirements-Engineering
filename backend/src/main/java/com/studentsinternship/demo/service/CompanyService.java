package com.studentsinternship.demo.service;


import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.company.CompanyDto;
import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.entity.Application;

import java.util.List;

public interface CompanyService {
    void createCompany(CompanyDto dto);

    boolean companyExists(CompanyDto dto);

    List<InternshipDto> listInternshipAnnouncements(CompanyDto dto);

    void createInternshipAnnouncement(InternshipDto dto);

    boolean internshipExists(InternshipDto dto);

    List<ApplicationDto> listInternshipApplications(InternshipDto internshipDto);
}
