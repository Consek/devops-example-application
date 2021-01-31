package com.example.backend.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import com.example.backend.Util;

@Service
public class RedisService {

  private final InstanceRepository instanceRepository;


  public RedisService(InstanceRepository instanceRepository) {
    this.instanceRepository = instanceRepository;
  }

  public List<Instance> getInstances(){
    return this.instanceRepository.findAll();
  }
  //TODO
  public void markMyselfAsActive(){
  }
  //TODO add shutdown hook
  public void removeYourself(){
    this.instanceRepository.deleteByHostname(Util.getHostName());
  }

  public void saveNewInstance(Instance instance){
    this.instanceRepository.save(instance);
  }

  @PostConstruct()
  public void loadTestData(){
    Instance instance = new Instance(Util.getHostName() , "v3");
    this.instanceRepository.save(instance);
  }
}
