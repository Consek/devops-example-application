package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

  public static void main(String[] args) {
    Runtime.getRuntime().addShutdownHook(new Thread() {
    @Override
      public void run() {
        System.out.println("Inside Add Shutdown Hook");
      }
    });
    SpringApplication.run(BackendApplication.class, args);
  }

}
