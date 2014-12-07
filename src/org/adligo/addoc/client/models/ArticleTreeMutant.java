package org.adligo.addoc.client.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleTreeMutant implements I_ArticleTree {
  private String date;
  private Map<String,ArticleMutant> articles = new HashMap<String,ArticleMutant>();
  private ArticleTreePathsMutant paths = new ArticleTreePathsMutant();
  
  public ArticleTreeMutant(String niceDate) {
    setDate(niceDate);
  }
  
  public ArticleTreeMutant(I_ArticleTree other) {
    setDate(other.getDate());
  }
  
  public void addArticle(String path, ArticleMutant article) {
    articles.put(path, article);
    paths.addPath(path);
  }
  
  public List<String> getTop() {
    return paths.getTop();
  }
    
  public List<String> getSub(String parentPath) {
    return paths.getSub(parentPath);
  }

  /* (non-Javadoc)
   * @see org.adligo.addoc.shared.models.I_ArticleTree#getDate()
   */
  @Override
  public String getDate() {
    return date;
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.shared.models.I_ArticleTree#getArticle(java.lang.String)
   */
  @Override
  public I_Article getArticle(String path) {
    return articles.get(path);
  }

  private void setDate(String date) {
    if (date == null) {
      throw new IllegalArgumentException("Null article tree dates are not allowed.");
    }
    this.date = date;
  }

  @Override
  public List<String> getAllOrdered() {
    return paths.getAllOrdered();
  }

  @Override
  public List<I_Article> getArticles() {
     return new ArrayList<I_Article>(articles.values());
  }
}
