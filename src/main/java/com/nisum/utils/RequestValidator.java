package com.nisum.utils;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

  @Autowired
  private Validator validador;

  public void doValidate(Object bean) {

    Set<ConstraintViolation<Object>> result = validador.validate(bean);
    if (!result.isEmpty()) {
      StringBuilder sb = new StringBuilder("Bean state is invalid: ");
      for (Iterator<ConstraintViolation<Object>> it = result.iterator(); it.hasNext();) {
        ConstraintViolation<Object> violation = it.next();

        sb.append(violation.getPropertyPath()).append(" - ").append(violation.getMessage());
        if (it.hasNext()) {
          sb.append("; ");
        }
      }
      throw new BeanInitializationException(sb.toString());
    }
  }

  public void validate(Object obj) {
    Set<ConstraintViolation<Object>> violations = validador.validate(obj);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

}
