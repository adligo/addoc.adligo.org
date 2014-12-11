package org.adligo.addoc.client.ui;

import org.adligo.addoc.client.models.I_SimplePanelContent;

public interface I_AdColumnView extends I_SizedComposite {
  public I_AdView addAd(I_SimplePanelContent adContent);
}
