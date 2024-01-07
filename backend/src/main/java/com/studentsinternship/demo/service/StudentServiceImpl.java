package com.studentsinternship.demo.service;

import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.dto.student.StudentDto;
import com.studentsinternship.demo.entity.Application;
import com.studentsinternship.demo.entity.Internship;
import com.studentsinternship.demo.entity.Student;
import com.studentsinternship.demo.entity.User;
import com.studentsinternship.demo.mapper.ApplicationMapper;
import com.studentsinternship.demo.mapper.InternshipMapper;
import com.studentsinternship.demo.mapper.StudentMapper;
import com.studentsinternship.demo.repository.ApplicationRepository;
import com.studentsinternship.demo.repository.InternshipRepository;
import com.studentsinternship.demo.repository.StudentRepository;
import com.studentsinternship.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final InternshipRepository internshipRepository;

    @Autowired
    private final ApplicationRepository applicationRepository;

    @Autowired
    private final StudentMapper studentMapper;

    @Autowired
    private final InternshipMapper internshipMapper;

    @Autowired
    private final ApplicationMapper applicationMapper;

    @Override
    public void createApplicantProfile(StudentDto dto) {
        Student student = studentMapper.dtoToEntity(dto);
        studentRepository.save(student);
    }

    @Override
    public void editApplicantProfile(StudentDto dto) {
        Student student = studentMapper.dtoToEntity(dto);
        studentRepository.save(student);
    }

    @Override
    public boolean studentExists(StudentDto dto) {
        Optional<Student> student = studentRepository.findById(dto.getId());
        return student.isPresent();
    }

    @Override
    public boolean userExists(StudentDto dto) {
        User user = userRepository.findByEmail(dto.getUser().getEmail());
        return user != null;
    }

    @Override
    public List<InternshipDto> listInternshipAnnouncements() {
        List<Internship> internships = internshipRepository.findAll();
        List<InternshipDto> internshipDtos = new ArrayList<>();
        for (Internship internship : internships) {
            internshipDtos.add(internshipMapper.entityToDto(internship));
        }
        return internshipDtos;
    }
    @Override
    public List<InternshipDto> listFilteredInternshipAnnouncements(Long companyId, String jobTitle, String position) {
        List<InternshipDto> allInternships = listInternshipAnnouncements();
        return allInternships.stream()
                .filter(internship -> (companyId == null || internship.getCompany().getId().equals(companyId)) &&
                        (jobTitle == null || internship.getJobTitle().equalsIgnoreCase(jobTitle)) &&
                        (position == null || internship.getPosition().equalsIgnoreCase(position)))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDto> getInternshipApplicationsForStudent(Long studentId) {
        List<Application> allApplications = applicationRepository.findAll();
        List<Application> filteredApplications = allApplications.stream()
                .filter(application -> application.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());

        return applicationMapper.entitiesToDtos(filteredApplications);
    }

    @Override
    public List<InternshipDto> searchInternshipAnnouncements(String query) {
        List<InternshipDto> allInternships = listInternshipAnnouncements();
        if (query == null || query.isEmpty()) {
            return allInternships;
        }
        String lowerCaseQuery = query.toLowerCase();
        return allInternships.stream()
                .filter(internship ->
                        (internship.getJobTitle() != null && internship.getJobTitle().toLowerCase().contains(lowerCaseQuery)) ||
                                (internship.getJobDescription() != null && internship.getJobDescription().toLowerCase().contains(lowerCaseQuery)) ||
                                (internship.getPosition() != null && internship.getPosition().toLowerCase().contains(lowerCaseQuery)) ||
                                (internship.getRequirements() != null && internship.getRequirements().toLowerCase().contains(lowerCaseQuery)) ||
                                (internship.getDuration() != null && internship.getDuration().toLowerCase().contains(lowerCaseQuery)) ||
                                (internship.getSchedule() != null && internship.getSchedule().toLowerCase().contains(lowerCaseQuery)) ||
                                (internship.getLocation() != null && internship.getLocation().toLowerCase().contains(lowerCaseQuery)) ||
                                (internship.getProcess() != null && internship.getProcess().toLowerCase().contains(lowerCaseQuery)) ||
                                (internship.getDeadline() != null && internship.getDeadline().toLowerCase().contains(lowerCaseQuery)) ||
                                (internship.getBenefits() != null && internship.getBenefits().toLowerCase().contains(lowerCaseQuery))
                )
                .collect(Collectors.toList());
    }

    @Override
    public void applyForInternship(ApplicationDto dto) {
        Application application = applicationMapper.dtoToEntity(dto);
        applicationRepository.save(application);
    }

    @Override
    public boolean applicationExists(ApplicationDto dto) {
        Optional<Application> application = applicationRepository.findById(dto.getId());
        return application.isPresent();
    }
}
