package com.studentsinternship.demo.service;

import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.dto.student.StudentDto;
import com.studentsinternship.demo.entity.Application;
import com.studentsinternship.demo.entity.Internship;
import com.studentsinternship.demo.entity.Student;
import com.studentsinternship.demo.entity.User;
import com.studentsinternship.demo.mapper.ApplicationMapper;
import com.studentsinternship.demo.mapper.InternshipMapper;
import com.studentsinternship.demo.mapper.StudentMapper;
import com.studentsinternship.demo.repository.ApplicationRepository;
import com.studentsinternship.demo.repository.InternshipRepository;
import com.studentsinternship.demo.repository.StudentRepository;
import com.studentsinternship.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final InternshipRepository internshipRepository;

    @Autowired
    private final ApplicationRepository applicationRepository;

    @Autowired
    private final StudentMapper studentMapper;

    @Autowired
    private final InternshipMapper internshipMapper;

    @Autowired
    private final ApplicationMapper applicationMapper;

    @Override
    public void createApplicantProfile(StudentDto dto) {
        Student student = studentMapper.dtoToEntity(dto);
        studentRepository.save(student);
    }

    @Override
    public void editApplicantProfile(StudentDto dto) {
        Student student = studentMapper.dtoToEntity(dto);
        studentRepository.save(student);
    }

    @Override
    public boolean studentExists(StudentDto dto) {
        Optional<Student> student = studentRepository.findById(dto.getId());
        return student.isPresent();
    }

    @Override
    public boolean userExists(StudentDto dto) {
        User user = userRepository.findByEmail(dto.getUser().getEmail());
        return user != null;
    }

    @Override
    public List<InternshipDto> listInternshipAnnouncements() {
        List<Internship> internships = internshipRepository.findAll();
        List<InternshipDto> internshipDtos = new ArrayList<>();
        for(Internship internship: internships) {
            internshipDtos.add(internshipMapper.entityToDto(internship));
        }
        return internshipDtos;
    }
    @Override
    public void applyForInternship(ApplicationDto dto) {
        Application application = applicationMapper.dtoToEntity(dto);
        applicationRepository.save(application);
    }

    @Override
    public boolean applicationExists(ApplicationDto dto) {
        Optional<Application> application = applicationRepository.findById(dto.getId());
        return application.isPresent();
    }
}
