package org.adligo.addoc.client.view;

import org.adligo.addoc.client.models.I_SimplePanelContent;
import org.adligo.addoc.client.ui.I_AdColumnView;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class AdColumnView extends AbstractSizedView implements I_AdColumnView {
  private int adds = 0;
  private VerticalPanel verticalPanel_;
  
  public AdColumnView() {
    verticalPanel_ = new VerticalPanel();
    initWidget(verticalPanel_);
  }

  public void addAd(I_SimplePanelContent adContent) {
    AdFrame adFrame = new AdFrame(adContent.getUrl());
    
    adFrame.setHeight("" + adContent.getHeight() + "px");
    adFrame.setWidth("" + adContent.getWidth() + "px");
    
    if (adds != 0) {
      SimplePanel sp = new SimplePanel();
      sp.setHeight("10px");
      verticalPanel_.add(sp);
    }
    adds++;
    verticalPanel_.add(adFrame);
  }
}
