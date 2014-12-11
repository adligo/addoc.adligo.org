package org.adligo.addoc.client.ui;

import org.adligo.addoc.client.models.I_Article;

import java.util.List;

public interface I_ArticleView extends I_SizedComposite {
  public void setTextHtml(String tHtml);
  /**
   * The url for the most current
   * content of this article.
   * @param url
   */
  public void setCurrentUrl(String url);
  /**
   * the url with the article version id,
   * which should never change the content
   * @param url
   */
  public void setHistoricalUrl(String url);
  
  public void setUpdated(String niceDate);
  public void setUpEnabled(boolean up);
  public void setPreviousEnabled(boolean p);
  public void setNextEnabled(boolean n);
  public void setHistoryEnabled(boolean history);
  public void addChildArticles(List<I_Article> articles);
}
