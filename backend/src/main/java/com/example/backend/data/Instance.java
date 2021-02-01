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

  public Instance(String hostname, String version, boolean isProxy, boolean isActive) {
    this.hostname = hostname;
    this.version = version;
    this.isProxy = isProxy;
    this.isActive = isActive;
  }


  public String getHostname() {
    return hostname;
  }

  public String getVersion() {
    return version;
  }

  public boolean getIsProxy() {
    return isProxy;
  }

  public void setProxy(boolean proxy) {
    isProxy = proxy;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public static Instance creteDefaultInstance(String hostname, String version){
    return new Instance(hostname, version, false, false);
  }
}
