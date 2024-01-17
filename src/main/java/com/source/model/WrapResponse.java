package com.source.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WrapResponse {

  private String resultCode;

  private String resultMsg;

  private Object contents;
}
