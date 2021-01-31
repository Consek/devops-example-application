package com.example.backend;

import com.example.backend.helpers.ShutdownThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {



  public static void main(String[] args) {
    Runtime.getRuntime().addShutdownHook(new ShutdownThread());
    SpringApplication.run(BackendApplication.class, args);
  }

}
