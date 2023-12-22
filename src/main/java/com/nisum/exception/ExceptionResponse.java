package com.nisum.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {

  private String mensaje;

  public ExceptionResponse(String mensaje) {
    this.mensaje = mensaje;
  }

}
