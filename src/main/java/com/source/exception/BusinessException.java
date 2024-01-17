package com.source.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
  private static final long serialVersionUID = 1L;

  private ErrorCode type;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getCode());
    this.type = errorCode;
  }
}
