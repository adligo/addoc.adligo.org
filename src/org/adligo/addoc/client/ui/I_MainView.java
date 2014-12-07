package org.adligo.addoc.client.ui;


public interface I_MainView {
  public void setMenuView(I_MenuView menuView);
  public I_ArticleView getArticleView();
  public void setArticleView(I_ArticleView articleView);
  public I_AdColumnView getAdColumnView();
  public void setAdColumnView(I_AdColumnView adColumnView);
  public I_MenuView getMenuView();
  public void show();
}
