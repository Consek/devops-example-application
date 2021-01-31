package com.example.backend.controller;

import com.example.backend.data.Instance;
import com.example.backend.Util;
import com.example.backend.data.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class InstanceController {

//  private final RedisService redisService;

//  @Autowired
//  public InstanceController(RedisService redisService) {
//    this.redisService = redisService;
//  }

  @GetMapping("/instances")
  public List<Instance> instances() {

    return List.of(new Instance("kdkjd", "dklfld", "test43"));


//    return redisService.getInstances();
  }

  @PostMapping("/instances")
  public ResponseEntity<String> saveInstance(@RequestBody Instance instance){
    System.out.println(instance);

//    this.redisService.saveNewInstance(instance);
    return ResponseEntity.ok().build();

  }
}
