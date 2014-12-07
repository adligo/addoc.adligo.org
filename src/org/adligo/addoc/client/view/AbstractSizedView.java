package org.adligo.addoc.client.view;

import com.google.gwt.user.client.ui.Composite;

import org.adligo.addoc.client.ui.Dimension;
import org.adligo.addoc.client.ui.I_Dimension;
import org.adligo.addoc.client.ui.I_SizedComposite;

public abstract class AbstractSizedView extends Composite implements I_SizedComposite {
  private I_Dimension height_;
  private I_Dimension width_;
  
  public I_Dimension getHeightDimension() {
    return height_;
  }
  
  public void setHeight(Dimension height) {
    this.height_ = height;
  }
  
  public I_Dimension getWidthDimension() {
    return width_;
  }
  
  public void setWidth(Dimension width) {
    this.width_ = width;
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
  }
  
  @Override
  public void setWidth(I_Dimension width) {
    width_ = width;
  }
}
