package com.example.backend.data;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Instance")
public class Instance implements Serializable {

  private final String id;
  private final String hostname;
  private final String version;

  public Instance(String id, String hostname, String version) {
    this.id = id;
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
