package com.nisum.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

  private UUID uuid;
  private LocalDateTime created;
  private LocalDateTime modified;
  private LocalDateTime last_login;
  private String token;
  private Boolean isactive;
}
