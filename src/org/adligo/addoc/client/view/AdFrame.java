package org.adligo.addoc.client.view;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Frame;

public class AdFrame extends Frame {

  public AdFrame(String url) {
    super(url);
    super.setStyleName(".adFrame");
    Element adElement = super.getElement();
    adElement.setAttribute("seamless", "seamless");
    adElement.setAttribute("scrolling", "no");
  }
}
