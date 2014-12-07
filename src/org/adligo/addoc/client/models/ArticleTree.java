package org.adligo.addoc.client.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleTree implements I_ArticleTree {
  private String date;
  private Map<String,Article> articles = new HashMap<String,Article>();
  private List<I_Article> articleList;
  private ArticleTreePathsMutant paths = new ArticleTreePathsMutant();
  private List<String> top = null;
  private Map<String,List<String>> subs = new HashMap<String,List<String>>();
  
  public ArticleTree(I_ArticleTree other) {
    List<String> ordered = other.getAllOrdered();
    for (String path: ordered) {
      articles.put(path, 
          new Article(other.getArticle(path)));
      paths.addPath(path);
    }
    top = Collections.unmodifiableList(paths.getTop());

    articleList = Collections.unmodifiableList(
        new ArrayList<Article>(articles.values()) );
  }
  
  public List<String> getTop() {
    return top;
  }
    
  public List<String> getSub(String parentPath) {
    List<String> toRet = subs.get(parentPath);
    if (toRet == null) {
      toRet = Collections.unmodifiableList(paths.getSub(parentPath));
      subs.put(parentPath, toRet);
    }
    return toRet;
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

  @Override
  public List<String> getAllOrdered() {
    return paths.getAllOrdered();
  }

  @Override
  public List<I_Article> getArticles() {
    // TODO Auto-generated method stub
    return articleList;
  }
}
