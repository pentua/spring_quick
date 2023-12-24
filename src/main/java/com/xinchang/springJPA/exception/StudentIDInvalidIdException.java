package com.xinchang.springJPA.exception;

public class StudentIDInvalidIdException extends RuntimeException{
  public StudentIDInvalidIdException(String message) {
    super(message);
  }
}
