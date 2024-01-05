package com.studentsinternship.demo.dto.user;

import com.studentsinternship.demo.entity.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String forename;
    private String surname;
    private String phoneNumber;
    private Role role;
}
