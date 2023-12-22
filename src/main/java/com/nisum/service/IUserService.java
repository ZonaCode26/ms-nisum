package com.nisum.service;

import com.nisum.model.dto.UserDto;
import com.nisum.model.dto.UserRequest;
import com.nisum.model.dto.UserResponse;
import com.nisum.model.entity.User;

public interface IUserService extends ICrud<User, Long> {

  UserResponse registrar(UserRequest request);

  UserDto getById(Long valueOf);

}
