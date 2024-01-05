package com.studentsinternship.demo.mapper;

import com.studentsinternship.demo.dto.user.UserDto;
import com.studentsinternship.demo.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto entityToDto(User entity);

    User dtoToEntity(UserDto dto);

    List<UserDto> entitiesToDtos(List<User> entities);

    List<User> dtosToEntities(List<UserDto> dtos);
}
