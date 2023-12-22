package com.nisum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.nisum.model.dto.UserDto;
import com.nisum.model.dto.UserRequest;
import com.nisum.model.dto.UserResponse;
import com.nisum.model.entity.User;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

  UserResponse toUserDto(User user);

  User toUser(UserRequest userRequest);

  UserDto toUserDto2(User user);
}
