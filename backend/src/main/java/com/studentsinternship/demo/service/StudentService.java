package com.studentsinternship.demo.service;

import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.dto.student.StudentDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StudentService {

    void createApplicantProfile(StudentDto dto);

    void editApplicantProfile(StudentDto dto);

    boolean studentExists(StudentDto dto);

    boolean userExists(StudentDto dto);

    List<InternshipDto> listInternshipAnnouncements();

    List<InternshipDto> listFilteredInternshipAnnouncements(String industry, String location,
                                                            Long salaryLowerBound, Long salaryUpperBound, Long durationLowerBound,
                                                            Long durationUpperBound);
    ApplicationDto viewInternshipApplication(Long applicationId);
    List<ApplicationDto> getInternshipApplicationsForStudent(Long studentId);

    List<InternshipDto> searchInternshipAnnouncements(String query);

    void applyForInternship(ApplicationDto dto);

    boolean applicationExists(ApplicationDto dto);

    StudentDto getStudent(Long id);

//    boolean userExists(RegisterDto dto);
//
//    StudentDto getUser(String email);
//
//    StudentDto getUserById(Long id);
//
//    StudentDto findUserById(Long id);
}

