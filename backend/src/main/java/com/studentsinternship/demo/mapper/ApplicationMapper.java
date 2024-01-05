package com.studentsinternship.demo.mapper;

import com.studentsinternship.demo.dto.application.ApplicationDto;
import com.studentsinternship.demo.entity.Application;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InternshipMapper.class, StudentMapper.class})
public interface ApplicationMapper {

    ApplicationDto entityToDto(Application entity);

    Application dtoToEntity(ApplicationDto dto);

    List<ApplicationDto> entitiesToDtos(List<Application> entities);

    List<Application> dtosToEntities(List<ApplicationDto> dtos);
}
