package com.studentsinternship.demo.service;

import com.studentsinternship.demo.dto.student.StudentDto;
import com.studentsinternship.demo.entity.Student;
import com.studentsinternship.demo.entity.User;
import com.studentsinternship.demo.mapper.StudentMapper;
import com.studentsinternship.demo.repository.StudentRepository;
import com.studentsinternship.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final StudentMapper studentMapper;

    @Override
    public void createApplicantProfile(StudentDto dto) {
        Student student = studentMapper.dtoToEntity(dto);
        studentRepository.save(student);
    }

    @Override
    public boolean studentExists(StudentDto dto) {
        User user = userRepository.findByEmail(dto.getUser().getEmail());
        return user != null;
    }
}
