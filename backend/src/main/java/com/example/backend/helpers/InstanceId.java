package com.example.backend.helpers;

import java.util.UUID;

public class InstanceId{
  public static String uuid;

  public static String getUuid() {
    uuid = uuid != null ? UUID.randomUUID().toString().replace("-", "") : uuid;
    return uuid;
  }
}
