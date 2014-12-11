package org.adligo.addoc.client.view;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Frame;

import org.adligo.addoc.client.ui.I_AdView;

public class AdView extends Frame implements I_AdView {
  private String url_;
  
  public AdView(String url) {
    super(url);
    url_ = url;
    super.setStyleName(".adFrame");
    Element adElement = super.getElement();
    adElement.setAttribute("seamless", "seamless");
    adElement.setAttribute("scrolling", "no");
  }

  @Override
  public void reloadAd() {
    super.setUrl(url_);
  }
}
