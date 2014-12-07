package org.adligo.addoc.client.models;

import java.util.List;

public interface I_ArticleContent {

  public I_Article getArticle(int id);

  public I_ArticleTree getLatestTree();

  public int getLatestTreeIndex();

  public I_ArticleTree getTree(int index);

  public I_ArticleTree getTree(String date);

  public List<I_Article> getArticles();

  public List<I_ArticleTree> getArticleTrees();
}