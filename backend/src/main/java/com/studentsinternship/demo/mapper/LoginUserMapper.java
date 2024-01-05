package com.studentsinternship.demo.mapper;

import com.studentsinternship.demo.dto.user.LoginUserDto;
import com.studentsinternship.demo.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoginUserMapper {

    LoginUserDto entityToDto(User entity);

    User dtoToEntity(LoginUserDto dto);

    List<LoginUserDto> entitiesToDtos(List<User> entities);

    List<User> dtosToEntities(List<LoginUserDto> dtos);

}
