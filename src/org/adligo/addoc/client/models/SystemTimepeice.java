package org.adligo.addoc.client.models;

public class SystemTimepeice implements I_Timepiece {

  @Override
  public long getTime() {
    return System.currentTimeMillis();
  }

}
