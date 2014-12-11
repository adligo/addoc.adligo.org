package org.adligo.addoc.client.ui;

import org.adligo.addoc.client.models.I_SimplePanelContent;
import org.adligo.addoc.client.ui.events.I_AddocHandler;

public interface I_MenuView  extends I_SizedComposite {
  public void setHandler(I_AddocHandler handler);
  public void setTitleImage(I_SimplePanelContent content);
  public I_AdView setAd(I_SimplePanelContent content);
  public void setLastModifiedDate(String date);
  public void render();
}