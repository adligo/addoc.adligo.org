package org.adligo.addoc.client.models;

import java.util.List;

public interface I_ArticleTree extends I_ArticleTreePaths {

  public abstract String getDate();

  public abstract I_Article getArticle(String path);

  public List<I_Article> getArticles();
}