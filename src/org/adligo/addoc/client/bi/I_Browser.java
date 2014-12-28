package org.adligo.addoc.client.bi;

import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.http.client.RequestCallback;

/**
 * This is a main interface for any interaction
 * with the browser that isn't available in JSE.
 * Or in other words a interface for GWT client
 * specific code. 
 * @author scott
 *
 */
public interface I_Browser {
  public String getBrowserAddress();
  public void doGet(String url, I_TextFileCallback callback);
  public void runAsync(RunAsyncCallback callback);
}
