package com.example.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation
  .GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class InstanceController {

  @GetMapping("/instance")
  public Instance instance() {
    return new Instance(Util.getHostName(), "v1");
  }
}
