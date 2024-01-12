package com.source.controller;

import com.source.model.request.QuartzRequest;
import com.source.model.response.QuartzSettingList;
import com.source.service.QuartzService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/quratz/setting")
public class QuartzController {
  private final QuartzService quartzService;

  @GetMapping("list")
  public QuartzSettingList getQuartzSchedulerSetting(QuartzRequest request){
    return quartzService.getQuartzSchedulerSetting(request);
  }
}
