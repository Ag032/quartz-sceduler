package com.source.job;

import lombok.extern.java.Log;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {
//getMergedJobDataMap
  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    context.getMergedJobDataMap();


    System.out.println("## test job Call");
  }
}
