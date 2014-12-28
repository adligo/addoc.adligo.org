package org.adligo.addoc.client.bi;

public interface I_TextFileCallback {
  public void onSuccess(String text);
  public void onFailure(Throwable error);
}
