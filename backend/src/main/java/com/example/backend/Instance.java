package com.example.backend;

public class Instance {

  private final String hostname;
  private final String version;

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
