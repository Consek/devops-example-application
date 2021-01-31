package com.example.backend.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

//@Service
public class RedisService {

  private final InstanceRepository instanceRepository;


  public RedisService(InstanceRepository instanceRepository) {
    this.instanceRepository = instanceRepository;
  }

  public List<Instance> getInstances(){
    return this.instanceRepository.findAll();
  }

  public void saveNewInstance(Instance instance){
    this.instanceRepository.save(instance);
  }

  @PostConstruct()
  public void loadTestData(){
    Instance instance = new Instance("1", "awesome host", "v3");
    this.instanceRepository.save(instance);
  }
}
