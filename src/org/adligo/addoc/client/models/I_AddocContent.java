package org.adligo.addoc.client.models;

public interface I_AddocContent {

  public abstract I_SimplePanelContent getTitleImage();

  public abstract I_SimplePanelContent getHeaderAd();

  public I_SimplePanelContent[] getRightFrames();

  public I_SimplePanelContent[] getLeftFrames();
  
  public I_ContentCache getContentCache();
}