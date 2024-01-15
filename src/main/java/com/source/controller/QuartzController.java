package com.source.controller;

import com.source.model.request.QuartzRequest;
import com.source.service.QuartzService;
import lombok.RequiredArgsConstructor;
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
  public void startQuartzSchedulerSetting() throws Exception{
    quartzService.startQuartzSchedulerSetting();
  }

  @GetMapping("scheduler")
  public void getQuartzScheduler() throws Exception{
    quartzService.getQuartzScheduler();
  }
}
