package com.source.service;

import com.source.exception.BusinessException;
import com.source.job.TestJob;
import com.source.model.request.JobRequest;
import com.source.model.response.QuartzSettingList;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.core.RemotableQuartzScheduler;
import org.quartz.core.jmx.JobDataMapSupport;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.scheduling.quartz.QuartzJobBean;
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
//  public void startQuartzSchedulerSetting() throws SchedulerException {
//    final var schedulerFactory = new StdSchedulerFactory();
//    final var scheduler = schedulerFactory.getScheduler();
//
//    final var job = JobBuilder.newJob(TestJob.class)
//        .withIdentity("testjob").build();
//
//    final var trigger = TriggerBuilder.newTrigger()
//        .withIdentity("DumbTrigger")
//        .startNow()
//        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//            .withIntervalInSeconds(2)
//            .repeatForever())
//        .build();
//
//    scheduler.scheduleJob(job, trigger);
//    scheduler.start();
//  }

  /**
   * 쿼츠 세팅
   * @param request
   * @throws Exception
   */
  public void schedulerSetting(JobRequest request) throws Exception {
    final var scheduler = new StdSchedulerFactory().getScheduler();
    final var classFile =  Class.forName("com.source.job."+request.getJobClassName());
    final var jobDataMap = JobDataMapSupport.newJobDataMap(Map.of("param", "request.get"));

    final var jobDetail = JobBuilder
        .newJob((Class<? extends Job>) classFile)
        .withIdentity(request.getSchedulerName(),request.getSchedulerGroupName())
//        .setJobData(jobDataMap)
        .withDescription(request.getDescription())
        .build();

    final var trigger = TriggerBuilder.newTrigger()
        .withIdentity(request.getTriggerName(),request.getTriggerGroupName())
        .withSchedule(CronScheduleBuilder.cronSchedule(request.getCron()))
        .build();

    scheduler.scheduleJob(jobDetail,trigger);

    scheduler.start();

  }

//  public void updateQuartzScheduler(JobRequest request) throws SchedulerException {
//    final var triggerSetting =
//        TriggerBuilder.newTrigger()
//            .withIdentity(request.getSchedulerName())
//            .withSchedule(CronScheduleBuilder.cronSchedule(request.getCron()))
//            .build();
//
//    scheduler.rescheduleJob(new TriggerKey(request.getSchedulerGroupName()), triggerSetting);
//  }
//
//  public void deleteQuartzScheduler(JobRequest request) throws Exception {
//    scheduler.unscheduleJob(new TriggerKey(request.getSchedulerName()));
//  }
//
//  public void pauseJob(JobRequest request) throws Exception{
//    final var jobKey = this.getJobKey(request);
//    scheduler.pauseJob(jobKey);
//  }
//
//  public void resumeJob(JobRequest request) throws Exception{
//    final var jobKey = this.getJobKey(request);
//    scheduler.resumeJob(jobKey);
//  }

  /**
   * 쿼츠 리스트
   * @param request
   * @throws Exception
   */
  public void getQuartzSchedulerList(JobRequest request) throws Exception{
    final var scheduler = new StdSchedulerFactory().getScheduler();
    final var schedulerGroupNameList = scheduler.getJobGroupNames();
    for(final var jobGroupName: schedulerGroupNameList){
      final var jobkeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroupName));

      for(final var jobKey : jobkeys){
        final var jobInfo = scheduler.getJobDetail(jobKey);
        final var jobDateInfo = scheduler.getTriggersOfJob(jobKey);
      }
    }
  }
}