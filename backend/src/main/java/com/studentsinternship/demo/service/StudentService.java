package com.studentsinternship.demo.service;

import com.studentsinternship.demo.dto.RegisterDto;
import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.dto.student.StudentDto;
import com.studentsinternship.demo.entity.Internship;
import com.studentsinternship.demo.entity.Student;
import com.studentsinternship.demo.entity.User;

import java.util.List;

public interface StudentService {

    void createApplicantProfile(StudentDto dto);

    void editApplicantProfile(StudentDto dto);

    boolean studentExists(StudentDto dto);

    boolean userExists(StudentDto dto);

    List<InternshipDto> listInternshipAnnouncements();

    void applyForInternship(ApplicationDto dto);

    boolean applicationExists(ApplicationDto dto);

//    boolean userExists(RegisterDto dto);
//
//    StudentDto getUser(String email);
//
//    StudentDto getUserById(Long id);
//
//    StudentDto findUserById(Long id);
}

