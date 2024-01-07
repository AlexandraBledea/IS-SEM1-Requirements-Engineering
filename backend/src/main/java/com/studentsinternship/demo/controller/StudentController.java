package com.studentsinternship.demo.controller;

import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.dto.student.StudentDto;
import com.studentsinternship.demo.service.CompanyService;
import com.studentsinternship.demo.service.StudentService;
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

    @GetMapping("/filter-internship-announcements")
    public ResponseEntity<List<InternshipDto>> filterInternshipAnnouncements(@RequestParam(required = false) Long companyId,
                                                                             @RequestParam(required = false) String jobTitle,
                                                                             @RequestParam(required = false) String position) {
        List<InternshipDto> internships = studentService.listFilteredInternshipAnnouncements(companyId, jobTitle, position);
        return new ResponseEntity<>(internships, HttpStatus.OK);
    }

    @GetMapping("/search-internship-announcements")
    public ResponseEntity<List<InternshipDto>> searchInternshipAnnouncements(
            @RequestParam(required = false) String query) {
        List<InternshipDto> internships = studentService.searchInternshipAnnouncements(query);
        return new ResponseEntity<>(internships, HttpStatus.OK);
    }

    @GetMapping("/view-internship-announcement")
    public ResponseEntity<InternshipDto> viewInternshipAnnouncement(@RequestBody Long internshipId) {
        InternshipDto internshipDto = companyService.getInternshipAnnouncements(internshipId);
        if (internshipDto != null)
            return new ResponseEntity<>(internshipDto, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/view-internship-applications")
    public ResponseEntity<List<ApplicationDto>> viewInternshipApplication(@RequestParam Long studentId) {
        List<ApplicationDto> applicationsDto = studentService.getInternshipApplicationsForStudent(studentId);
        return new ResponseEntity<>(applicationsDto, HttpStatus.OK);
    }

    @PostMapping("/apply-for-internship")
    public ResponseEntity<String> applyForInternship(@RequestBody ApplicationDto dto) {
        if (studentService.applicationExists(dto))
            return new ResponseEntity<>("The application already exists!", HttpStatus.OK);
        if (!studentService.studentExists(dto.getStudent()))
            return new ResponseEntity<>("The student is invalid!", HttpStatus.OK);
        if (!companyService.internshipExists(dto.getInternship()))
            return new ResponseEntity<>("The internship is invalid!", HttpStatus.OK);

        studentService.applyForInternship(dto);
        return new ResponseEntity<>("Application created successfully!", HttpStatus.OK);

    }
}
