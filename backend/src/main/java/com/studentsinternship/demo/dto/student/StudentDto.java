package com.studentsinternship.demo.dto.student;

import com.studentsinternship.demo.dto.user.UserDto;
import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private UserDto user;
    private int age;
    private String location;
    private String personalWebsite;
    private String currentInstitution;
    private String studyProgram;
    private String relevantCoursework;
    private String gpa;
    private String pastExperience;
    private String skills;
    private String projects;
    private String extracurricularActivities;
    private String languages;
    private String carrerObjectives;
    private String references;
    private String hobbies;
}
