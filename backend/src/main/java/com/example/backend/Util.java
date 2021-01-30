package com.example.backend;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class Util{
  public static String getHostName() {
    String hostName;

    try {
      hostName = InetAddress.getLocalHost().getHostName();
    } catch (java.net.UnknownHostException e) {
      hostName = "notSet";
    }
    return hostName;
  }
}
