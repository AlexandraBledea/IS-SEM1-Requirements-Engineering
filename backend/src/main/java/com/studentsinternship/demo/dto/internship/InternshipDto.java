package com.studentsinternship.demo.dto.internship;

import com.studentsinternship.demo.dto.company.CompanyDto;
import lombok.Data;

@Data
public class InternshipDto {
    private Long id;
    private CompanyDto company;
    private String jobTitle;
    private String jobDescription;
    private String position;
    private String requirements;
    private Long duration;
    private String schedule;
    private String location;
    private Long availablePositions;
    private Long salary;
    private String process;
    private String deadline;
    private String benefits;
    private String industry;
}
