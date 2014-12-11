package org.adligo.addoc.client.ui.events;

public interface I_AddocHandler {
  /**
   * 
   * @param event
   */
  public void onSimpleEvent(AddocEvent event);
  /**
   * This occurs when a user selects a article 
   * which is related to a tree.
   * @param articleId
   * @param treeId
   */
  public void onArticleClick(String [] nodePath);
  
  /**
   * This is a click from the article view's 
   * sub article list.
   * 
   * @param subArticleName
   */
  public void onSubArticleClick(String subArticleName);
}
