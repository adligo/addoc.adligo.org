package org.adligo.addoc.client.ui.handlers;

public interface I_ArticleNavigationHandler {
  /**
   * This occurs when a user selects a article 
   * which is related to a tree.
   * @param articleId
   * @param treeId
   */
  public void onArticleClick(int articleId, int treeId);
  /**
   * click on a article in the current article tree
   * @param articleId
   */
  public void onArticleClick(int articleId);
  /**
   * Navigate between articles in a tree.
   * @param e
   */
  public void onEvent(ArticleNavigationEvent e);
  
}
