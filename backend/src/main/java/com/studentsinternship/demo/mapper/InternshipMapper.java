package com.studentsinternship.demo.mapper;

import com.studentsinternship.demo.dto.internship.InternshipDto;
import com.studentsinternship.demo.entity.Internship;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CompanyMapper.class)
public interface InternshipMapper {

    InternshipDto entityToDto(Internship entity);

    Internship dtoToEntity(InternshipDto dto);

    List<InternshipDto> entitiesToDtos(List<Internship> entities);

    List<Internship> dtosToEntities(List<InternshipDto> dtos);
}
