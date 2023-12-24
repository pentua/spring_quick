package com.xinchang.springJPA.exception;

public class StudentNameEmptyException extends RuntimeException {
  public StudentNameEmptyException(String statement) {
    super(statement);
  }
}
