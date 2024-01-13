package com.studentsinternship.demo.dto.application;

import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.dto.student.StudentDto;
import lombok.Data;

@Data
public class ApplicationDto {
    private Long id;
    private String coverLetter;
    private String cv;
    private String others;
    private StudentDto student;
    private InternshipDto internship;
}
