package com.studentsinternship.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="students")
public class Student {

    @Id
    @Column(name="student_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name="student_id")
    private User user;

    @Column(name="age")
    private Long age;

    @Column(name="location")
    private String location;

    @Column(name="personal_website")
    private String personalWebsite;

    @Column(name="current_institution")
    private String currentInstitution;

    @Column(name="study_program")
    private String studyProgram;

    @Column(name="relevant_coursework")
    private String relevantCoursework;

    @Column(name="gpa")
    private String gpa;

    @Column(name="past_experience")
    private String pastExperience;

    @Column(name="skills")
    private String skills;

    @Column(name="projects")
    private String projects;

    @Column(name="extracurricular_activities")
    private String extracurricularActivities;

    @Column(name="languages")
    private String languages;

    @Column(name="carrer_objectives")
    private String carrerObjectives;

    @Column(name="references")
    private String references;

    @Column(name="hobbies")
    private String hobbies;

}
