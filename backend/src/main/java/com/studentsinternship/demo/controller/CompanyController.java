package com.studentsinternship.demo.controller;

import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.company.CompanyDto;
import com.studentsinternship.demo.dto.internship.CreateUpdateInternshipDto;
import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.dto.recruiter.RecruiterDto;
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

    @GetMapping("/get-recruiter")
    public ResponseEntity<RecruiterDto> getRecruiter(@RequestParam Long recruiterId) {
        return new ResponseEntity<>(companyService.getRecruiter(recruiterId), HttpStatus.OK);
    }

    @PostMapping("/add-company")
    public ResponseEntity<String> createCompany(@RequestBody CompanyDto dto) {
        if (!companyService.companyExists(dto)) {
            companyService.createCompany(dto);
            return new ResponseEntity<>("Company created successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The company announcement already exists!", HttpStatus.OK);
        }
    }

    @GetMapping("/list-internship-announcements")
    public ResponseEntity<List<InternshipDto>> listInternshipAnnouncements(@RequestParam Long recruiterId) {
            List<InternshipDto> internships = companyService.listInternshipAnnouncements(recruiterId);
            return new ResponseEntity<>(internships, HttpStatus.OK);
    }

    @GetMapping("/view-internship-announcement")
    public ResponseEntity<InternshipDto> viewInternshipAnnouncement(@RequestBody Long internshipId) {
        InternshipDto internshipDto = companyService.getInternshipAnnouncement(internshipId);
        if (internshipDto != null) {
            return new ResponseEntity<>(internshipDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete-internship-announcement")
    public ResponseEntity<String> deleteInternshipAnnouncement(@RequestParam Long internshipId) {
        if (companyService.deleteInternshipAnnouncement(internshipId)) {
            return new ResponseEntity<>("Internship announcement deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The Internship announcement could not be deleted!", HttpStatus.OK);
        }
    }

    @PostMapping("/create-internship-announcement")
    public ResponseEntity<String> createInternshipAnnouncement(@RequestBody CreateUpdateInternshipDto dto) {
        companyService.createInternshipAnnouncement(dto);
        return new ResponseEntity<>("Internship announcement created successfully!", HttpStatus.OK);
    }

    @PostMapping("/edit-internship-announcement")
    public ResponseEntity<String> editInternshipAnnouncement(@RequestBody CreateUpdateInternshipDto dto) {
        companyService.createInternshipAnnouncement(dto);
        return new ResponseEntity<>("Internship announcement updated successfully!", HttpStatus.OK);
    }

    @GetMapping("/list-all-applications")
    public ResponseEntity<List<ApplicationDto>> getAllApplications() {
        return new ResponseEntity<>(companyService.getAllApplications(), HttpStatus.OK);
    }

    @PostMapping("/list-internship-applications")
    public ResponseEntity<List<ApplicationDto>> listInternshipApplications(@RequestBody InternshipDto internshipDto) {
        if (companyService.internshipExists(internshipDto)) {
            List<ApplicationDto> internships = companyService.listInternshipApplications(internshipDto);
            return new ResponseEntity<>(internships, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @GetMapping("/view-internship-application")
    public ResponseEntity<ApplicationDto> viewInternshipApplications(@RequestBody Long applicationId) {
        ApplicationDto applicationDto = companyService.getInternshipApplication(applicationId);
        if (applicationDto != null) {
            return new ResponseEntity<>(applicationDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
