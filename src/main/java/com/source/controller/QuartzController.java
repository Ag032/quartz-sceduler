package com.source.controller;

import com.source.exception.BusinessException;
import com.source.service.QuartzService;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/quratz/setting")
public class QuartzController {
  private final QuartzService quartzService;

  @PostMapping("make")
  @ExceptionHandler(BusinessException.class)
  public void startQuartzSchedulerSetting() throws SchedulerException {
    quartzService.startQuartzSchedulerSetting();
  }

  @GetMapping("scheduler")
  public void getQuartzScheduler() throws Exception{
    quartzService.getQuartzScheduler();
  }
}
