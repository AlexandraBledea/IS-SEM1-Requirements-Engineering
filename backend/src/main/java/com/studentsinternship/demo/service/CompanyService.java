package com.studentsinternship.demo.service;


import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.company.CompanyDto;
import com.studentsinternship.demo.dto.internship.CreateUpdateInternshipDto;
import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.entity.Company;

import java.util.List;

public interface CompanyService {

    Company findCompanyById(Long id);
    void createCompany(CompanyDto dto);

    boolean companyExists(CompanyDto dto);

    List<InternshipDto> listInternshipAnnouncements(CompanyDto dto);

    InternshipDto getInternshipAnnouncement(Long internshipId);

    ApplicationDto getInternshipApplication(Long applicationId);

    boolean deleteInternshipAnnouncement(Long internshipId);

    void createInternshipAnnouncement(CreateUpdateInternshipDto dto);

    boolean internshipExists(InternshipDto dto);

    List<ApplicationDto> listInternshipApplications(InternshipDto internshipDto);
}
