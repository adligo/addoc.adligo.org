package org.adligo.addoc.client.view;

import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.adligo.addoc.client.models.I_SimplePanelContent;
import org.adligo.addoc.client.ui.Dimension;
import org.adligo.addoc.client.ui.I_AdColumnView;
import org.adligo.addoc.client.ui.I_AdView;
import org.adligo.addoc.client.ui.I_Dimension;

public class AdColumnView extends AbstractSizedView implements I_AdColumnView {
  private int adds = 0;
  private VerticalPanel verticalPanel_;
  
  public AdColumnView() {
    verticalPanel_ = new VerticalPanel();
    initWidget(verticalPanel_);
  }

  public I_AdView addAd(I_SimplePanelContent adContent) {
    AdView adView = new AdView(adContent.getUrl());
    
    I_Dimension height = super.getHeightDimension();
    I_Dimension width = super.getWidthDimension();
    
    int inHeight = adContent.getHeight();
    int inWidth =  adContent.getWidth();
    
    if (height == null) {
      height = new Dimension(inHeight);
    } else {
      height = new Dimension(inHeight + height.getDim());
    }
    super.setHeight(height);
    if (width == null) {
      width = new Dimension(inWidth);
    } else if (inWidth > width.getDim()){
      width = new Dimension(inWidth);
    }
    super.setWidth(width);
    verticalPanel_.setWidth(width.getDimension());
    adView.setHeight("" + adContent.getHeight() + "px");
    adView.setWidth("" + adContent.getWidth() + "px");
    
    if (adds != 0) {
      SimplePanel sp = new SimplePanel();
      sp.setHeight("10px");
      verticalPanel_.add(sp);
    }
    adds++;
    verticalPanel_.add(adView);
    return adView;
  }
}
