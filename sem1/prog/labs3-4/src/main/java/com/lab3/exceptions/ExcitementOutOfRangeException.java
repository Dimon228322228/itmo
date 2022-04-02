package com.lab3.exceptions;

public class ExcitementOutOfRangeException extends Exception {
  public String getMessage() {
      return(" ExcitementLevel вышел за границы ");
  }
}
