package org.adligo.addoc.client.ui;

import org.adligo.addoc.client.ui.events.I_AddocHandler;

public interface I_ArticleTreeView {
  public void clearNodes();
  public void addNode(String [] nodePath);
  public void setHandler(I_AddocHandler handler);
  public void showPreviousTreeLink(boolean show);
  public void showNextTreeLink(boolean show);
  public void showCurrentTreeLink(boolean show);
  public void show();
  public void hide();
  public void setDateModified(String date);
}
