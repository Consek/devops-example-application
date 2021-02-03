package com.example.backend.helpers;

import com.example.backend.data.InstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShutdownThread extends Thread {


  private InstanceRepository instanceRepository;


  public ShutdownThread(InstanceRepository instanceRepository) {
    this.instanceRepository = instanceRepository;
  }

  @Override
  public void run() {
    System.out.println("shutdown");
    this.instanceRepository.deleteById(Util.getHostName());
  }
}
