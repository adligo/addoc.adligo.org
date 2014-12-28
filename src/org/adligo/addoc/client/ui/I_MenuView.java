package org.adligo.addoc.client.ui;

import org.adligo.addoc.client.models.I_SimplePanelContent;
import org.adligo.addoc.client.ui.events.I_AddocHandler;

public interface I_MenuView {
  public void setHandler(I_AddocHandler handler);
  public void setTitleImage(I_SimplePanelContent content);
  public I_FrameView setAd(I_SimplePanelContent content);
  public void setLastModifiedDate(String date);
}
