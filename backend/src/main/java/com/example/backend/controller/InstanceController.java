package com.example.backend.controller;

import com.example.backend.data.Instance;
import com.example.backend.Util;
import com.example.backend.data.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation
  .GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class InstanceController {

  private final RedisService redisService;

  @Autowired
  public InstanceController(RedisService redisService) {
    this.redisService = redisService;
  }

  @GetMapping("/instances")
  public List<Instance> instances() {

    return redisService.getInstances();
  }
}
