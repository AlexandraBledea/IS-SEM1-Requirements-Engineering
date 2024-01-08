package com.studentsinternship.demo.service;

import com.studentsinternship.demo.dto.RegisterDto;
import com.studentsinternship.demo.dto.user.LoginUserDto;
import com.studentsinternship.demo.dto.user.UserDto;
import com.studentsinternship.demo.entity.Company;
import com.studentsinternship.demo.entity.Recruiter;
import com.studentsinternship.demo.entity.Student;
import com.studentsinternship.demo.entity.User;
import com.studentsinternship.demo.mapper.UserMapper;
import com.studentsinternship.demo.repository.CompanyRepository;
import com.studentsinternship.demo.repository.RecruiterRepository;
import com.studentsinternship.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.studentsinternship.demo.repository.UserRepository;
import java.util.Optional;
import com.studentsinternship.demo.entity.enums.Role;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final UserMapper userMapper;

    @Autowired final CompanyRepository companyRepository;

    @Autowired final RecruiterRepository recruiterRepository;

    public UserDto authentication(LoginUserDto dto) {
        return userMapper.entityToDto(userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()));
    }

    public User getUserInformation(LoginUserDto dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(dto.getPassword(), user.getPassword()))
                return user;
            else return null;
        }
        return userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
    }

    @Override
    public void createUser(RegisterDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = User.builder().email(dto.getEmail())
                .password(dto.getPassword())
                .surname(dto.getSurname())
                .forename(dto.getForename())
                .phoneNumber(dto.getPhoneNumber())
                .role(dto.getRole())
                .build();
        User savedUser = userRepository.save(user);
        
        if(savedUser.getRole().equals(Role.STUDENT)) {
            Student student = Student.builder()
                    .user(savedUser).build();

            studentRepository.save(student);
        }
        else {
            Company company = companyRepository.findByName(dto.getCompanyName());
            Recruiter recruiter = Recruiter.builder()
                    .company(company)
                    .user(user)
                    .build();

            recruiterRepository.save(recruiter);
        }
    }

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
