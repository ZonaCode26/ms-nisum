package com.nisum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nisum.model.dto.UserRequest;
import com.nisum.model.dto.UserResponse;
import com.nisum.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

  @Autowired
  private IUserService userService;

  @PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public UserResponse registrar(@RequestBody UserRequest request) {
    return userService.registrar(request);
  }

}
