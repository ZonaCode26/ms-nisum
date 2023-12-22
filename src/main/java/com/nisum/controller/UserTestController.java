package com.nisum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.model.dto.UserDto;
import com.nisum.model.entity.Phone;
import com.nisum.model.entity.User;
import com.nisum.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserTestController {

  @Autowired
  private IUserService userService;

  @GetMapping(value = "/all", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<User> all() {
    return userService.listar();
  }

  @GetMapping(value = "/all-phone", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Phone> allPhone() {
    for (User uitem : userService.listar()) {
      System.err.println(uitem.getEmail());
      for (Phone item : uitem.getPhones()) {
        System.err.println("entro");
        System.err.println(item.toString());
      }

    }
    return userService.listar().get(0).getPhones();
  }

  @GetMapping(value = "/find-id", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDto findId() {

    return userService.getById(Long.valueOf("1"));
  }

}
