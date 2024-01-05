package com.studentsinternship.demo.dto.recruiter;

import com.studentsinternship.demo.dto.company.CompanyDto;
import com.studentsinternship.demo.dto.user.UserDto;
import lombok.Data;

@Data
public class RecruiterDto {
    private Long id;
    private UserDto user;
    private CompanyDto company;
}
