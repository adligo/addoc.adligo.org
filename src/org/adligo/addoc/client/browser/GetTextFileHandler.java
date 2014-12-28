package org.adligo.addoc.client.browser;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

import org.adligo.addoc.client.bi.I_TextFileCallback;

public class GetTextFileHandler implements RequestCallback {
  public static final String UNKNOWN_STATUS_CODE = "Unknown status code ";
  public static final String GET_TEXT_FILE_HANDLER_REQUIRES_A_CALLBACK = "GetTextFileHandler requires a callback.";
  private I_TextFileCallback callback_;
  
  public GetTextFileHandler(I_TextFileCallback callback, String url) {
    if (callback == null) {
      throw new IllegalArgumentException(GET_TEXT_FILE_HANDLER_REQUIRES_A_CALLBACK);
    }
    callback_ = callback;
    RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, url);
    rb.setCallback(this);
    try {
      rb.send();
    } catch (RequestException e) {
      callback.onFailure(e);
    }
  }

  @Override
  public void onResponseReceived(Request request, Response response) {
    String text = response.getText();
    int statusCode = response.getStatusCode();
    if (statusCode == 200) {
      callback_.onSuccess(text);
    } else {
      callback_.onFailure(new IllegalStateException(UNKNOWN_STATUS_CODE + statusCode));
    }
    callback_ = null;
  }

  @Override
  public void onError(Request request, Throwable exception) {
    callback_.onFailure(exception);
    callback_ = null;
  }
}
