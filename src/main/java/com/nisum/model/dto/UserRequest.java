package com.nisum.model.dto;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.nisum.validation.PasswordPattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

  private String name;

  @Pattern(
      regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
      message = "Formato de correo electrónico no válido")
  private String email;
  @PasswordPattern
  private String password;
  private List<PhoneRequest> phones;
}
