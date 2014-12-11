package org.adligo.addoc.client.ui;

public interface I_DialogView {
  /**
   * (true, false) and close buttons are mutually exclusive.
   */
  public void setTrueFalseButtonsVisible();
  public void setTrueButtonText(String text);
  public void setFalseButtonText(String text);
  
  /**
   * (true, false) and close buttons are mutually exclusive.
   */
  public void setCloseButtonVisible();
  public void setCloseButtonText(String text);
  
  public void setMessageText(String text, boolean wordWrap);
  
  public void show();
  public void hide();
  public void setHeight(String height);
  public void setWidth(String width);
  /**
   * Close button with default height and width and any other defaults.
   */
  public void resetDefaults();
}
