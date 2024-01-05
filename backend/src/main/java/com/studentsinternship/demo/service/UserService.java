package com.studentsinternship.demo.service;

import com.studentsinternship.demo.dto.RegisterDto;
import com.studentsinternship.demo.dto.user.LoginUserDto;
import com.studentsinternship.demo.dto.user.UserDto;
import com.studentsinternship.demo.entity.User;

public interface UserService {

    UserDto authentication(LoginUserDto dto);

    User getUserInformation(LoginUserDto dto);

    UserDto createUser(RegisterDto dto);

    boolean userExists(RegisterDto dto);

    UserDto getUser(String email);

    UserDto getUserById(Long id);

    UserDto findUserById(Long id);
}
