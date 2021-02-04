package com.example.backend.service;

import com.example.backend.helpers.ShutdownThread;
import com.example.backend.helpers.Util;
import com.example.backend.data.Instance;
import com.example.backend.data.InstanceRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RedisService {

  private final InstanceRepository instanceRepository;
  private final ShutdownThread shutdownThread;


  public RedisService(InstanceRepository instanceRepository, ShutdownThread shutdownThread) {
    this.instanceRepository = instanceRepository;
    this.shutdownThread = shutdownThread;
  }

  public List<Instance> getInstances(){
    List<Instance> allInstances = this.instanceRepository.findAll();
    markMyselfAsActive(allInstances);
    return allInstances;
  }

  public void removeYourself(){
    System.out.println("Removing myself from cache");
    this.instanceRepository.deleteById(Util.getHostName());
  }

  public void removeInstance(String hostname){
    this.instanceRepository.deleteById(hostname);
  }

  public void saveNewInstance(Instance instance){
    this.instanceRepository.save(instance);
  }

  @PostConstruct()
  public void registerMyself(){
    System.out.println("Registering my hostname");
    Instance instance = Instance.creteDefaultInstance(Util.getHostName() , "v3");
    this.instanceRepository.save(instance);

    Runtime.getRuntime().addShutdownHook(this.shutdownThread);
  }

  private void markMyselfAsActive(List<Instance> allInstances){
    allInstances.stream().filter(elem -> elem.getHostname().equals(Util.getHostName()))
        .findFirst().ifPresent(elem -> elem.setActive(true));

  }

  public void clearCache() {
    this.instanceRepository.deleteAll();
  }
}
