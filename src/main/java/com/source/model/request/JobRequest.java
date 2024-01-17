package com.source.model.request;

import lombok.Getter;

@Getter
public class JobRequest {
  private String schedulerName;

  private String schedulerGroupName;

  private String description;

  private String cron;

}
