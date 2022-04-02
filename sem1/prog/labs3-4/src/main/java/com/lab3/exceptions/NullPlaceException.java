package com.lab3.exceptions;

public class NullPlaceException extends RuntimeException {
  public String getMessage() {
      return(" Character's place is null! ");
  }
}
