package org.adligo.addoc.client.models;

/**
 * This class contains information about
 * something that goes in a panel,
 * and the size of the panel.
 * It can be used for image tags,
 * frame tags exc.
 * 
 * @author scott
 *
 */
public class SimplePanelContent implements I_SimplePanelContent {
  /**
   * the width of the add in pixels
   */
  private int width_;
  /**
   * the height of the add in pixels
   */
  private int height_;

  private String url_;
  
  public SimplePanelContent(int height, int width, String url) {
    height_ = height;
    width_ = width;
    url_ = url;
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_SimplePanelContent#getWidth()
   */
  @Override
  public int getWidth() {
    return width_;
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_SimplePanelContent#getHeight()
   */
  @Override
  public int getHeight() {
    return height_;
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_SimplePanelContent#getUrl()
   */
  @Override
  public String getUrl() {
    return url_;
  }
}
