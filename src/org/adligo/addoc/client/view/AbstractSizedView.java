package org.adligo.addoc.client.view;

import com.google.gwt.user.client.ui.Composite;

import org.adligo.addoc.client.ui.I_Dimension;
import org.adligo.addoc.client.ui.I_SizedComposite;

public abstract class AbstractSizedView extends Composite implements I_SizedComposite {
  private I_Dimension height_;
  private I_Dimension width_;
  
  public I_Dimension getHeightDimension() {
    return height_;
  }
  
  public I_Dimension getWidthDimension() {
    return width_;
  }
  
  public void setWidth(I_Dimension width) {
    this.width_ = width;
    if (width_.isPct()) {
      this.setWidth("" + width_.getDim() + "%");
    } else {
      this.setWidth("" + width_.getDim() + "px");
    }
  }
  
  public String getHeight() {
    return height_.getDimension();
  }
  
  public String getWidth() {
    return width_.getDimension();
  }
  
  @Override
  public void setHeight(I_Dimension height) {
    height_ = height;
    if (height_.isPct()) {
      this.setHeight("" + height_.getDim() + "%");
    } else {
      this.setHeight("" + height_.getDim() + "px");
    }
  }
  
}
