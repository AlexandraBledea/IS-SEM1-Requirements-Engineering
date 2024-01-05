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
@Table(name="recruiters")
public class Recruiter {

    @Id
    @Column(name="recruiter_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name="recruiter_id")
    private User user;

    @OneToOne
    @JoinColumn(name="company_id")
    @JsonBackReference
    private Company company;
}
