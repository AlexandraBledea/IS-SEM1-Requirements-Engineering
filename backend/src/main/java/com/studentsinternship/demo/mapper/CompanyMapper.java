package com.studentsinternship.demo.mapper;

import com.studentsinternship.demo.dto.company.CompanyDto;
import com.studentsinternship.demo.entity.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDto entityToDto(Company entity);

    Company dtoToEntity(CompanyDto dto);

    List<CompanyDto> entitiesToDtos(List<Company> entities);

    List<Company> dtosToEntities(List<CompanyDto> dtos);
}
