package com.nisum.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public MessageException(String mensaje) {
    super(mensaje);
  }
}
