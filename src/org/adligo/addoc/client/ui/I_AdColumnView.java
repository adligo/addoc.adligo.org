package org.adligo.addoc.client.ui;

import org.adligo.addoc.client.models.I_SimplePanelContent;

public interface I_AdColumnView {
  public void addPanel(I_SimplePanelContent adContent);
  public I_FrameView addAd(I_SimplePanelContent adContent);
}
