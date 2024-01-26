package com.source.controller;

import com.source.exception.BusinessException;
import com.source.model.request.JobRequest;
import com.source.service.QuartzService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/quartz/setting")
public class QuartzController {
  private final QuartzService quartzService;

  @PostMapping("make")
  public void schedulerSetting(@RequestBody JobRequest request) throws Exception{
    quartzService.schedulerSetting(request);
  }

//  @GetMapping("get")
//  public void getQuartzSchedulerList(JobRequest request) throws Exception{
//    quartzService.getQuartzSchedulerList(request);
//  }
}
