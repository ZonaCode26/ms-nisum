package com.nisum.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nisum.exception.MessageException;
import com.nisum.mapper.UserMapper;
import com.nisum.model.dto.UserDto;
import com.nisum.model.dto.UserRequest;
import com.nisum.model.dto.UserResponse;
import com.nisum.model.entity.User;
import com.nisum.repository.IGenericRepository;
import com.nisum.repository.IUserRepository;
import com.nisum.security.JwtTokenUtil;
import com.nisum.service.IUserService;
import com.nisum.utils.RequestValidator;
import lombok.SneakyThrows;

@Service
public class UserServiceImpl extends CrudImpl<User, Long> implements IUserService {

  @Autowired
  private IUserRepository repository;

  @Autowired
  private RequestValidator requestValidator;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  protected IGenericRepository<User, Long> getRepository() {
    return repository;
  }

  @SneakyThrows
  @Override
  public UserResponse registrar(UserRequest request) {
    requestValidator.validate(request);

    if (Boolean.TRUE.equals(repository.existsByEmail(request.getEmail()))) {
      throw new MessageException("El correo ya registrado");
    }

    User user = repository.save(setUser(request));
    return userMapper.toUserDto(user);
  }

  private User setUser(UserRequest request) {
    LocalDateTime now = LocalDateTime.now();
    User user = userMapper.toUser(request);

    user.getPhones().forEach(phone -> phone.setUser(user));
    user.setUuid(UUID.nameUUIDFromBytes(user.getEmail().getBytes()));
    user.setCreated(now);
    user.setLast_login(now);
    user.setIsactive(Boolean.TRUE);
    user.setToken(jwtTokenUtil.generateJwtToken(user.getEmail()));
    return user;
  }

  @Override
  public UserDto getById(Long valueOf) {

    return userMapper.toUserDto2(repository.findById(valueOf).orElseGet(null));
  }
}
