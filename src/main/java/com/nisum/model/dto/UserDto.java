package com.nisum.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.nisum.model.entity.Phone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

  private Long id;

  private UUID uuid;
  private String name;
  private String email;
  private String password;
  private List<Phone> phones;
  private LocalDateTime created;
  private LocalDateTime modified;
  private String token;
  private Boolean isactive;
  private LocalDateTime last_login;
}
