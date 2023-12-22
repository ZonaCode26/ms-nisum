package com.nisum.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nisum.utils.AppConfig;

public class PasswordPatternValidator implements ConstraintValidator<PasswordPattern, String> {

  @Autowired
  private AppConfig appConfig;

  @Override
  public void initialize(PasswordPattern constraintAnnotation) {
    // Puedes realizar inicializaciones si es necesario
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true; // Puedes ajustar según tus necesidades
    }

    String pattern = appConfig.getPasswordPattern();
    return value.matches(pattern);
  }
}
