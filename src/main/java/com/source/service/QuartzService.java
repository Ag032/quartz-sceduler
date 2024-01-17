package com.source.service;

import com.source.exception.BusinessException;
import com.source.job.TestJob;
import com.source.model.request.JobRequest;
import lombok.RequiredArgsConstructor;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *exception 처리
 *
 * 스케줄러 세팅
 *
 * job setting
 *
 * trigger setting
 *
 * job 삭제
 * job 일시정지
 * job 실행
 *
 * trigger 삭제
 * tigger 일시정지
 * tigger 실행
 *
 * cron trigger setting (매주 지정)
 * simple trigger setting (매주지장)
 *
 * cron 자동으로 세팅
 *
 */

@Service
@RequiredArgsConstructor
public class QuartzService {
  private final Scheduler scheduler;

  //test code
  public void startQuartzSchedulerSetting() throws SchedulerException {
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

    scheduler.scheduleJob(job, trigger);
  }


  public void aaa(JobRequest request) throws SchedulerException {
    final var schedulerFactory = new StdSchedulerFactory();
  }

  public void updateQuartzScheduler(JobRequest request) throws SchedulerException {
    final var triggerSetting =
        TriggerBuilder.newTrigger()
            .withIdentity(request.getSchedulerName())
            .withSchedule(CronScheduleBuilder.cronSchedule(request.getCron()))
            .build();

    scheduler.rescheduleJob(new TriggerKey(request.getSchedulerGroupName()), triggerSetting);
  }

  public void deleteQuartzScheduler(JobRequest request) throws Exception {
    scheduler.unscheduleJob(new TriggerKey(request.getSchedulerName()));
  }

  public void pauseJob(JobRequest request) throws Exception{
    final var jobKey = this.getJobKey(request);
    scheduler.pauseJob(jobKey);
  }

  public void resumeJob(JobRequest request) throws Exception{
    final var jobKey = this.getJobKey(request);
    scheduler.resumeJob(jobKey);
  }

  public void getQuartzScheduler() throws Exception{
    final var scheduler = new StdSchedulerFactory().getScheduler();
    for(final var jobKey : scheduler.getJobKeys(null)){
      final var jobInfo = scheduler.getTriggersOfJob(jobKey);

    }
  }

  private JobKey getJobKey(JobRequest request) throws Exception{
    final var scheduler = new StdSchedulerFactory().getScheduler();
    for(final var jobKey : scheduler.getJobKeys(null)){
      if(jobKey.getName().equals(request.getSchedulerName()))
        return jobKey;
    }
    return null;
  }
}