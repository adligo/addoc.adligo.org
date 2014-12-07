package org.adligo.addoc.client.ui;


public interface I_ViewFactory {
  public I_MainView createMainView();
  public I_MenuView createMenuView();
  public I_AdColumnView createAdColumnView();
  public I_ArticleView createArticleView();
  public I_ArticleTreeView createArticleTreeView();
}
