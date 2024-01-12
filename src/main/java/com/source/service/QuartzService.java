package com.source.service;

import com.source.model.request.QuartzRequest;
import com.source.model.response.QuartzSettingList;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuartzService {
  private final Scheduler scheduler;
  public QuartzSettingList getQuartzSchedulerSetting(QuartzRequest request){
    return null;
  }
}