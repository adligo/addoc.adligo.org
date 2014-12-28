package org.adligo.addoc.client.ui;

import org.adligo.addoc.client.models.I_ArticleBrief;

import java.util.List;

public interface I_ArticleView {
  public void setTitle(String title);
  public void setHeight(String height);
  
  public void setVersions(String previousDate, String lastModified);
  
  public void setTextHtml(String tHtml);
  /**
   * The url for the most current
   * content of this article.
   * @param url
   */
  public void setTopicUrl(String url);
  /**
   * the url with the article version id,
   * which should never change the content
   * @param url
   */
  public void setArticleUrl(String url);
  public void setUpEnabled(boolean up);
  public void setPreviousEnabled(boolean p);
  public void setNextEnabled(boolean n);
  public void setChildArticles(int [] articleTreePath, List<I_ArticleBrief> articles);
}
