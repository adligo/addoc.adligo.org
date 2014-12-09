package org.adligo.addoc.client.ui.handlers;

public interface I_AddocHandler {
  public void onSimpleEvent(AddocEvent event);
  /**
   * This occurs when a user selects a article 
   * which is related to a tree.
   * @param articleId
   * @param treeId
   */
  public void onArticleClick(String [] nodePath);
}
