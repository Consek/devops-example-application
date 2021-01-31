package com.example.backend.helpers;

import com.example.backend.data.InstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class ShutdownThread extends Thread {

  @Autowired
  private InstanceRepository instanceRepository;


  public ShutdownThread() {
  }

  @Override
  public void run() {
    System.out.println("shutdown");
    this.instanceRepository.deleteByHostname(Util.getHostName());
  }
}
