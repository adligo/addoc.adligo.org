package org.adligo.addoc.client.ui;

/**
 * implementations of this class are also expected to 
 * extend Composite, so they can be cast to a Composite.
 * @author scott
 *
 */
public interface I_SizedComposite {
  public I_Dimension getHeightDimension();
  public I_Dimension getWidthDimension();
  public String getHeight();
  public String getWidth();
  public void setHeight(I_Dimension height);
  public void setWidth(I_Dimension width);
}
