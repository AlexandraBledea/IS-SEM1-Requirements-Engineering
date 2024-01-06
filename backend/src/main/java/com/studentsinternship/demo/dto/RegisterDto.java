package com.studentsinternship.demo.dto;

import com.studentsinternship.demo.entity.enums.Role;
import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String password;
    private String forename;
    private String surname;
    private String phoneNumber;
    private Role role;
    private String companyName;
}
