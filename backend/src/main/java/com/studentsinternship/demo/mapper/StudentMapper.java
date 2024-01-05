package com.studentsinternship.demo.mapper;


import com.studentsinternship.demo.dto.student.StudentDto;
import com.studentsinternship.demo.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface StudentMapper {

    StudentDto entityToDto(Student entity);

    Student dtoToEntity(StudentDto dto);

    List<StudentDto> entitiesToDtos(List<Student> entities);
    List<Student> dtosToEntities(List<StudentDto> dtos);
}
