package com.studentsinternship.demo.controller;

import com.studentsinternship.demo.dto.RegisterDto;
import com.studentsinternship.demo.dto.TokenDto;
import com.studentsinternship.demo.dto.student.StudentDto;
import com.studentsinternship.demo.dto.user.LoginUserDto;
import com.studentsinternship.demo.entity.User;
import com.studentsinternship.demo.service.StudentService;
import com.studentsinternship.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin()
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody StudentDto dto) {
        if (!studentService.studentExists(dto)) {
            studentService.createApplicantProfile(dto);
            return new ResponseEntity<>("User created succesfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The user already exists!", HttpStatus.OK);
        }
    }
}
