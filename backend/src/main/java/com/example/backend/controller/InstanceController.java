package com.example.backend.controller;

import com.example.backend.data.Instance;
import com.example.backend.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class InstanceController {

  private final RedisService redisService;

  @Autowired
  public InstanceController(RedisService redisService) {
    this.redisService = redisService;
  }

  @GetMapping("/healthcheck")
  public String healthcheck() {
    return "Application OK";
  }

  @GetMapping("/instances")
  public List<Instance> instances() {

    return redisService.getInstances();
  }

  @PostMapping("/instance")
  public ResponseEntity<String> saveInstance(@RequestBody Instance instance){
    this.redisService.saveNewInstance(instance);
    return ResponseEntity.ok().build();

  }

  @DeleteMapping("/instance/{hostname}")
  public ResponseEntity<String> deleteInstance(@PathVariable String hostname){
    this.redisService.removeInstance(hostname);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/instances")
  public ResponseEntity<String> clearRedis(){
    this.redisService.clearCache();
    return ResponseEntity.ok().build();
  }
}
