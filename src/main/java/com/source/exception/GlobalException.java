package com.source.exception;

import com.source.model.WrapResponse;
import javax.lang.model.type.ErrorType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
  @ExceptionHandler(BusinessException.class)
  private ResponseEntity<?> handleAiuException(final BusinessException e) {
    e.printStackTrace();
    final var type = e.getType();
    return ResponseEntity.status(type.getStatus())
        .body(createResponse(type));
  }

  protected WrapResponse createResponse(final ErrorCode type) {
    return WrapResponse.builder().resultCode(type.getCode()).resultMsg(type.getMessage()).build();
  }
}
