package com.nisum.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class AppConfig {

  @Value("${com.nisum.pattern.validation.password}")
  private String passwordPattern;
}
