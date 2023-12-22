package com.nisum.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordPatternValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordPattern {
  String message() default "Contraseña no válida";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
