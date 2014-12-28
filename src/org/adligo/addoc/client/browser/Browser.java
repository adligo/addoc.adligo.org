package org.adligo.addoc.client.browser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;

import org.adligo.addoc.client.bi.I_Browser;
import org.adligo.addoc.client.bi.I_TextFileCallback;

import java.util.HashMap;
import java.util.Map;

public class Browser implements I_Browser {
  
  @Override
  public String getBrowserAddress() {
    return Window.Location.getHref();
  }

  @Override
  public void doGet(String url, I_TextFileCallback callback) {
    new GetTextFileHandler(callback, url);
  }

  @Override
  public void runAsync(RunAsyncCallback callback) {
     GWT.runAsync(callback);
  }


}
