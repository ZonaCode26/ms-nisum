package com.nisum.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.nisum.model.dto.UserRequest;
import com.nisum.model.dto.UserResponse;
import com.nisum.service.IUserService;
import com.nisum.util.FileUtils;
import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @InjectMocks
  UserController userController;

  @Mock
  private IUserService userService;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @SneakyThrows
  @Test
  void Given_UserDataOk_When_registrar_Then_saveSuccess() {

    String loadRequest = FileUtils.loadJson("/data/registrar-user.json");
    UserResponse mockResponse = new UserResponse();
    mockResponse.setToken("token");
    UUID uuidTest = UUID.randomUUID();
    mockResponse.setUuid(uuidTest);

    when(userService.registrar(any(UserRequest.class))).thenReturn(mockResponse);

    mockMvc
        .perform(post("/user/registrar").contentType(MediaType.APPLICATION_JSON)
            .content(loadRequest).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.token").value(mockResponse.getToken()))
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.uuid").value(mockResponse.getUuid().toString()));
  }
}
