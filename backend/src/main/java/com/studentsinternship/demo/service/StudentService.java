package com.studentsinternship.demo.service;

import com.studentsinternship.demo.dto.RegisterDto;
import com.studentsinternship.demo.dto.student.StudentDto;
import com.studentsinternship.demo.entity.Student;
import com.studentsinternship.demo.entity.User;

public interface StudentService {

    void createApplicantProfile(StudentDto dto);

    boolean studentExists(StudentDto dto);

//    boolean userExists(RegisterDto dto);
//
//    StudentDto getUser(String email);
//
//    StudentDto getUserById(Long id);
//
//    StudentDto findUserById(Long id);
}

