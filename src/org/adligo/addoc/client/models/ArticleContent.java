package org.adligo.addoc.client.models;

import java.util.Collections;
import java.util.List;

public class ArticleContent  implements I_ArticleContent {
  private ArticleContentMutant mutant;
  private List<I_Article> articles;
  private List<I_ArticleTree> articleTrees;
  
  public ArticleContent(I_ArticleContent other) {
    mutant = new ArticleContentMutant(other);
    articles = Collections.unmodifiableList(mutant.getArticles());
    articleTrees = Collections.unmodifiableList(mutant.getArticleTrees());
  }
  
  public Article getArticle(int id) {
    return mutant.getArticle(id);
  }

  public ArticleTree getLatestTree() {
    return mutant.getLatestTree();
  }

  public int getLatestTreeIndex() {
    return mutant.getLatestTreeIndex();
  }

  public ArticleTree getTree(int index) {
    return mutant.getTree(index);
  }

  public ArticleTree getTree(String date) {
    return mutant.getTree(date);
  }

  public List<I_Article> getArticles() {
    return articles;
  }

  public List<I_ArticleTree> getArticleTrees() {
    return articleTrees;
  }
  
}
