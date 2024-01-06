package com.studentsinternship.demo.controller;

import com.studentsinternship.demo.dto.RegisterDto;
import com.studentsinternship.demo.dto.TokenDto;
import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.dto.student.StudentDto;
import com.studentsinternship.demo.dto.user.LoginUserDto;
import com.studentsinternship.demo.entity.User;
import com.studentsinternship.demo.service.CompanyService;
import com.studentsinternship.demo.service.StudentService;
import com.studentsinternship.demo.service.UserService;
import com.studentsinternship.demo.utils.annotations.AllowStudent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin()
@Slf4j
public class StudentController {

    private final StudentService studentService;

    private final CompanyService companyService;

    public StudentController(StudentService studentService, CompanyService companyService) {
        this.studentService = studentService;
        this.companyService = companyService;
    }

    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody StudentDto dto) {
        if (studentService.userExists(dto)) {
            studentService.createApplicantProfile(dto);
            return new ResponseEntity<>("Student created successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The student already exists!", HttpStatus.OK);
        }
    }

    @PutMapping("/edit-student")
    public ResponseEntity<String> editStudent(@RequestBody StudentDto dto) {
        if (studentService.studentExists(dto)) {
            studentService.editApplicantProfile(dto);
            return new ResponseEntity<>("Student updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The student couldn't be found exists!", HttpStatus.OK);
        }
    }

    @GetMapping("/list-internship-announcements")
    public ResponseEntity<List<InternshipDto>> listInternshipAnnouncements() {
        List<InternshipDto> internships = studentService.listInternshipAnnouncements();
        return new ResponseEntity<>(internships, HttpStatus.OK);
    }

    @PostMapping("/apply-for-internship")
    public ResponseEntity<String> applyForInternship(@RequestBody ApplicationDto dto) {
        if (!studentService.applicationExists(dto) &&
                (studentService.studentExists(dto.getStudent()) && companyService.internshipExists(dto.getInternship()))) {
            studentService.applyForInternship(dto);
            return new ResponseEntity<>("Application created successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The application already exists!", HttpStatus.OK);
        }
    }
}
