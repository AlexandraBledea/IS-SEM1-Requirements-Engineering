package com.studentsinternship.demo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="application_id")
    private Long id;

    @Column(name="cover_letter")
    private String coverLetter;

    @Column(name="cv")
    private String cv;

    @Column(name="others")
    private String others;

    @ManyToOne()
    @JoinColumn(name="student_id")
    @JsonBackReference
    private Student student;

    @ManyToOne()
    @JoinColumn(name="internship_id")
    @JsonBackReference
    private Internship internship;
}
