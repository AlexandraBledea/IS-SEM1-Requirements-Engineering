package com.studentsinternship.demo.service;

import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.company.CompanyDto;
import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.entity.Application;
import com.studentsinternship.demo.entity.Company;
import com.studentsinternship.demo.entity.Internship;
import com.studentsinternship.demo.mapper.ApplicationMapper;
import com.studentsinternship.demo.mapper.CompanyMapper;
import com.studentsinternship.demo.mapper.InternshipMapper;
import com.studentsinternship.demo.repository.ApplicationRepository;
import com.studentsinternship.demo.repository.CompanyRepository;
import com.studentsinternship.demo.repository.InternshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    private final InternshipRepository internshipRepository;

    @Autowired
    private final ApplicationRepository applicationRepository;

    @Autowired
    private final CompanyMapper companyMapper;

    @Autowired
    private final InternshipMapper internshipMapper;

    @Autowired
    private final ApplicationMapper applicationMapper;

    @Override
    public void createInternshipAnnouncement(InternshipDto dto) {
        Internship internship = internshipMapper.dtoToEntity(dto);
        internshipRepository.save(internship);
    }

    @Override
    public boolean internshipExists(InternshipDto dto) {
        Optional<Internship> internship = internshipRepository.findById(dto.getId());
        return internship.isPresent();
    }

    @Override
    public void createCompany(CompanyDto dto) {
        Company company = companyMapper.dtoToEntity(dto);
        companyRepository.save(company);
    }

    @Override
    public boolean companyExists(CompanyDto dto) {
        Optional<Company> company = companyRepository.findById(dto.getId());
        return company.isPresent();
    }

    @Override
    public List<InternshipDto> listInternshipAnnouncements(CompanyDto dto) {
        List<Internship> internships = internshipRepository.findAll().stream()
                .filter(application -> application.getCompany() != null)
                .filter(application -> dto.getId().equals(application.getCompany().getId()))
                .collect(Collectors.toList());
        List<InternshipDto> internshipDtos = new ArrayList<>();
        for(Internship internship: internships) {
            internshipDtos.add(internshipMapper.entityToDto(internship));
        }
        return internshipDtos;
    }

    @Override
    public List<ApplicationDto> listInternshipApplications(InternshipDto internshipDto) {
        List<Application> applications = applicationRepository.findAll().stream()
                .filter(application -> application.getInternship() != null && application.getInternship().getCompany() != null)
                .filter(application -> internshipDto.getCompany().getId().equals(application.getInternship().getCompany().getId()))
                .collect(Collectors.toList());
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        for(Application application: applications) {
            applicationDtos.add(applicationMapper.entityToDto(application));
        }
        return applicationDtos;
    }
}
