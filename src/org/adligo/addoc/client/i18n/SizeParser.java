package org.adligo.addoc.client.i18n;

public class SizeParser {
  private String height_;
  private String width_;
  
  public SizeParser(String size) {
    int comma = size.indexOf(",");
    height_ = size.substring(0,  comma);
    width_ = size.substring(comma + 1, size.length());
  }
  
  public String getHeight() {
    return height_;
  }
  
  public String getWidth() {
    return width_;
  }
}
