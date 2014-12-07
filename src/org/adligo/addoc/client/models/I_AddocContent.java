package org.adligo.addoc.client.models;

public interface I_AddocContent {

  public abstract I_ArticleContent getArticleContent();

  public abstract I_SimplePanelContent getTitleImage();

  public abstract I_SimplePanelContent getHeaderAd();

  public I_SimplePanelContent[] getRightAds();

}