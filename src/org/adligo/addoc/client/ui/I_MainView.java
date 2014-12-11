package org.adligo.addoc.client.ui;


public interface I_MainView {
  public void setMenuView(I_MenuView menuView);
  public I_ArticleView getArticleView();
  public void setArticleView(I_ArticleView articleView);
  public I_AdColumnView getRightAdColumnView();
  public void setRightAdColumnView(I_AdColumnView adColumnView);
  public I_AdColumnView getLeftAdColumnView();
  public void setLeftAdColumnView(I_AdColumnView adColumnView);
  public I_MenuView getMenuView();
  public void show();
  /**
   * the browsers window url
   * @return
   */
  public String getBrowserUrl();
}
