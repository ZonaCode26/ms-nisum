package com.nisum.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import com.nisum.mapper.UserMapper;
import com.nisum.model.dto.UserRequest;
import com.nisum.model.dto.UserResponse;
import com.nisum.model.entity.User;
import com.nisum.repository.IUserRepository;
import com.nisum.security.JwtTokenUtil;
import com.nisum.utils.RequestValidator;
import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


  @InjectMocks
  private UserServiceImpl userServiceImpl;

  @Mock
  private IUserRepository repository;

  @Mock
  private RequestValidator requestValidator;

  @Spy
  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @Mock
  private JwtTokenUtil jwtTokenUtil;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  @SneakyThrows
  @Test
  void Given_UserDataOk_When_registrar_Then_saveSuccess() {
    UserRequest userRequest = new UserRequest();
    userRequest.setEmail("test@dom.com");

    User user = new User();
    user.setPhones(new ArrayList<>());
    user.setEmail(userRequest.getEmail());

    doNothing().when(requestValidator).validate(any(UserRequest.class));
    when(repository.existsByEmail(anyString())).thenReturn(Boolean.FALSE);
    when(userMapper.toUser(any(UserRequest.class))).thenReturn(user);
    when(repository.save(any())).thenReturn(new User());

    UserResponse response = userServiceImpl.registrar(userRequest);
    assertNotNull(response);
    verify(jwtTokenUtil, times(1)).generateJwtToken(anyString());
  }
}
