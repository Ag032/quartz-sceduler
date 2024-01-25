package com.source.model.response;

import lombok.Builder;

@Builder
public class QuartzSettingList {
  private String schedulerName;

  private String schedulerGroupName;

  private String cron;
}
