package com.source.model.request;

import com.source.model.response.QuartzSettingList;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import org.quartz.Job;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Getter
public class JobRequest {
  private String schedulerName;

  private String schedulerGroupName;

  private String triggerName;

  private String triggerGroupName;

  private String description;

  private String cron;

  private String jobClassName;
}
