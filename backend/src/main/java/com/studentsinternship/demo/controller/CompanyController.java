package com.studentsinternship.demo.controller;

import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.company.CompanyDto;
import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin()
@Slf4j
public class CompanyController {

    @Autowired
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/add-company")
    public ResponseEntity<String> createInternshipAnnouncement(@RequestBody CompanyDto dto) {
        if (!companyService.companyExists(dto)) {
            companyService.createCompany(dto);
            return new ResponseEntity<>("Company created successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The company announcement already exists!", HttpStatus.OK);
        }
    }

    @GetMapping("/list-internship-announcements")
    public ResponseEntity<List<InternshipDto>> listInternshipAnnouncements(@RequestBody CompanyDto dto) {
        if(companyService.companyExists(dto)) {
            List<InternshipDto> internships = companyService.listInternshipAnnouncements(dto);
            return new ResponseEntity<>(internships, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/create-internship-announcement")
    public ResponseEntity<String> createInternshipAnnouncement(@RequestBody InternshipDto dto) {
        if (!companyService.internshipExists(dto)) {
            companyService.createInternshipAnnouncement(dto);
            return new ResponseEntity<>("Internship announcement created successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The internship announcement already exists!", HttpStatus.OK);
        }
    }

    @PostMapping("/list-internship-applications")
    public ResponseEntity<List<ApplicationDto>> listInternshipApplications(@RequestBody InternshipDto internshipDto) {
        if(companyService.internshipExists(internshipDto)) {
            List<ApplicationDto> internships = companyService.listInternshipApplications(internshipDto);
            return new ResponseEntity<>(internships, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
