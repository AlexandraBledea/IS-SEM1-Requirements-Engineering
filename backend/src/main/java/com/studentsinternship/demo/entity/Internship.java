package com.studentsinternship.demo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="internships")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="internship_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="company_id")
    @JsonBackReference
    private Company company;

    @Column(name="job_title")
    private String jobTitle;

    @Column(name="job_description")
    private String jobDescription;

    @Column(name="position")
    private String position;

    @Column(name="requirements")
    private String requirements;

    @Column(name="duration")
    private String duration;

    @Column(name="schedule")
    private String schedule;

    @Column(name="location")
    private String location;

    @Column(name="available_positions")
    private Long availablePositions;

    @Column(name="salary")
    private Long salary;

    @Column(name="process")
    private String process;

    @Column(name="deadline")
    private String deadline;

    @Column(name="benefits")
    private String benefits;

}
