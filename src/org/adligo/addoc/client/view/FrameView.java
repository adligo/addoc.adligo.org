package org.adligo.addoc.client.view;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Frame;

import org.adligo.addoc.client.ui.I_FrameView;

public class FrameView extends Frame implements I_FrameView {
  private String url_;
  
  public FrameView(String url) {
    super(url);
    url_ = url;
    super.setStyleName(".adFrame");
    Element adElement = super.getElement();
    adElement.setAttribute("seamless", "seamless");
    adElement.setAttribute("scrolling", "no");
    adElement.setAttribute("border", "0");
    adElement.setAttribute("frameborder", "0");
    adElement.setAttribute("hspace", "0");
    adElement.setAttribute("vspace", "0");
          
  }

  @Override
  public void reloadAd() {
    super.setUrl(url_);
  }
}
