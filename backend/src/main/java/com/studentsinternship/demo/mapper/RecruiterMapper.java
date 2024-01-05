package com.studentsinternship.demo.mapper;

import com.studentsinternship.demo.dto.recruiter.RecruiterDto;
import com.studentsinternship.demo.entity.Recruiter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CompanyMapper.class})
public interface RecruiterMapper {

    RecruiterDto entityToDto(Recruiter entity);

    Recruiter dtoToEntity(RecruiterDto dto);

    List<RecruiterDto> entitiesToDtos(List<Recruiter> entities);

    List<Recruiter> dtosToEntities(List<RecruiterDto> dtos);
}