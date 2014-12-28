package org.adligo.addoc.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.adligo.addoc.client.models.I_SimplePanelContent;
import org.adligo.addoc.client.ui.I_AdColumnView;
import org.adligo.addoc.client.ui.I_FrameView;

public class AdColumnView extends Composite implements I_AdColumnView {
 private VerticalPanel verticalPanel_;
  
  public AdColumnView() {
    verticalPanel_ = new VerticalPanel();
    initWidget(verticalPanel_);
  }

  public I_FrameView addAd(I_SimplePanelContent adContent) {
    String url = adContent.getUrl();
    
    FrameView frameView = new FrameView(url);
    
    String height = adContent.getHeight();
    String width =  adContent.getWidth();
    
    verticalPanel_.setHeight(height);
    verticalPanel_.setWidth(width);
    frameView.setHeight(height);
    frameView.setWidth(width);
    
    verticalPanel_.add(frameView);
    return frameView;
  }

  @Override
  public void addPanel(I_SimplePanelContent content) {
    
    SimplePanel sp = new SimplePanel();
    sp.setHeight(content.getHeight());
    sp.setWidth(content.getWidth());
    
    verticalPanel_.add(sp);
  }

}
