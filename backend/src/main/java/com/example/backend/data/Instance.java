package com.example.backend.data;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@RedisHash("Instance")
public class Instance implements Serializable {
  @Id()
  private  String hostname;
  private  String version;

  public Instance() {
  }

  public Instance(String hostname, String version) {
    this.hostname = hostname;
    this.version = version;
  }

  public String gethostname() {
    return hostname;
  }

  public String getversion() {
    return version;
  }
}
