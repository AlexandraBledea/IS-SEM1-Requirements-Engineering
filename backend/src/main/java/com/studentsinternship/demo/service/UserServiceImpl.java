package com.studentsinternship.demo.service;

import com.studentsinternship.demo.dto.RegisterDto;
import com.studentsinternship.demo.dto.user.LoginUserDto;
import com.studentsinternship.demo.dto.user.UserDto;
import com.studentsinternship.demo.entity.User;
import com.studentsinternship.demo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.studentsinternship.demo.repository.UserRepository;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService{

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserMapper userMapper;

    public UserDto authentication(LoginUserDto dto) {
        return userMapper.entityToDto(userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()));
    }

//    public User getUserInformation(LoginUserDto dto) {
//        User user = userRepository.findByEmail(dto.getEmail());
//        if (user != null) {
//            if (passwordEncoder.matches(dto.getPassword(), user.getPassword()))
//                return user;
//            else return null;
//        }
//        return userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
//
//    }
//
//    @Override
//    public UserDto createUser(RegisterDto dto) {
//        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
//        User user = User.builder().email(dto.getEmail())
//                .password(dto.getPassword())
//                .surname(dto.getSurname())
//                .forename(dto.getForename())
//                .phoneNumber(dto.getPhoneNumber())
//                .role(dto.getRole())
//                .build();
//        User savedUser = userRepository.save(user);
//        return userMapper.entityToDto(savedUser);
//    }

    @Override
    public boolean userExists(RegisterDto dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        return user != null;
    }

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isEmpty()) {
            UserDto userDto = userMapper.entityToDto(user.get());
            return userDto;
        } else return null;
    }

    @Override
    public UserDto getUser(String email) {
        User user = this.userRepository.findByEmail(email);
        return userMapper.entityToDto(user);

    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return this.userMapper.entityToDto(user.get());
    }

}
