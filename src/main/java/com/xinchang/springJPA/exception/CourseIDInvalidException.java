package com.xinchang.springJPA.exception;

public class CourseIDInvalidException extends RuntimeException {

  public CourseIDInvalidException(String message) {
    super(message);
  }
}
