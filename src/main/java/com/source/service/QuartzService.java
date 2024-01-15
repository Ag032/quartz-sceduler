package com.source.service;

import com.source.job.TestJob;
import com.source.model.request.QuartzRequest;
import lombok.RequiredArgsConstructor;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuartzService {
  private final Scheduler scheduler;
  public void startQuartzSchedulerSetting() throws Exception{
    final var schedulerFactory = new StdSchedulerFactory();
    final var scheduler = schedulerFactory.getScheduler();
    scheduler.start();

    final var job = JobBuilder.newJob(TestJob.class)
        .withIdentity("testjob").build();

    final var trigger = TriggerBuilder.newTrigger()
        .withIdentity("DumbTrigger")
        .startNow()
        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
            .withIntervalInSeconds(2)
            .repeatForever())
        .build();

    scheduler.scheduleJob(job,trigger);
  }

  public void getQuartzScheduler() throws Exception{
    final var scheduler = new StdSchedulerFactory().getScheduler();
    for(final var jobKey : scheduler.getJobKeys(null)){
      scheduler.getTriggersOfJob(jobKey);
    }
  }
}