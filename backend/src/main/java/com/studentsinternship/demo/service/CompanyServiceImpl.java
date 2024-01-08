package com.studentsinternship.demo.service;

import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.company.CompanyDto;
import com.studentsinternship.demo.dto.internship.CreateUpdateInternshipDto;
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
    public Company findCompanyById(Long id){
        Optional<Company> company = companyRepository.findById(id);

        return company.orElse(null);
    }

    public Internship createUpdateInternshipDtoToInternship(CreateUpdateInternshipDto dto){
        Company company = findCompanyById(dto.getCompanyId());
        return Internship.builder()
                .company(company)
                .duration(dto.getDuration())
                .availablePositions(dto.getAvailablePositions())
                .jobTitle(dto.getJobTitle())
                .location(dto.getLocation())
                .position(dto.getPosition())
                .process(dto.getProcess())
                .salary(dto.getSalary())
                .requirements(dto.getRequirements())
                .jobDescription(dto.getJobDescription())
                .schedule(dto.getSchedule())
                .deadline(dto.getDeadline())
                .benefits(dto.getBenefits())
                .industry(dto.getIndustry())
                .build();
    }

    @Override
    public void createInternshipAnnouncement(CreateUpdateInternshipDto dto) {
        Internship internship = createUpdateInternshipDtoToInternship(dto);
        internshipRepository.save(internship);
    }

    @Override
    public boolean internshipExists(InternshipDto dto) {
        Optional<Internship> internship = internshipRepository.findById(dto.getId());
        return internship.isPresent();
    }

    @Override
    public void createCompany(CompanyDto dto) {
        Company company = Company.builder()
                .name(dto.getName())
                        .build();
        Company company1 = companyRepository.save(company);
        System.out.println(company1);
    }

    @Override
    public boolean companyExists(CompanyDto dto) {
         Company company = companyRepository.findByName(dto.getName());

         return company != null;
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
    public InternshipDto getInternshipAnnouncements(Long internshipId) {
        Optional<Internship> internship = internshipRepository.findById(internshipId);
        return internship.map(internshipMapper::entityToDto).orElse(null);
    }
    @Override
    public ApplicationDto getInternshipApplication(Long applicationId) {
        Optional<Application> application = applicationRepository.findById(applicationId);
        return application.map(applicationMapper::entityToDto).orElse(null);
    }
    @Override
    public boolean deleteInternshipAnnouncement(Long internshipId) {
        if (internshipRepository.findById(internshipId).isPresent()){
            internshipRepository.deleteById(internshipId);
            return true;
        }
        return false;
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
