package com.nisum.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneRequest {

  private String number;
  private String citycode;
  private String contrycode;
}
