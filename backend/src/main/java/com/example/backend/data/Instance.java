package com.example.backend.data;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@RedisHash("Instance")
public class Instance implements Serializable {
  @Id()
  private  String hostname;
  private  String version;
  private  boolean isProxy;
  private  boolean isActive;

  public Instance() {
  }

  public Instance(String hostname, String version) {
    this.hostname = hostname;
    this.version = version;
    this.isProxy = false;
    this.isActive = false;
  }

  public String getHostname() {
    return hostname;
  }

  public String getVersion() {
    return version;
  }

  public boolean isProxy() {
    return isProxy;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }
}
