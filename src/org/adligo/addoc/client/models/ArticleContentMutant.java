package org.adligo.addoc.client.models;

import com.google.gwt.core.shared.GWT;

import org.adligo.addoc.client.i18n.AddocI18nConstants;

import java.util.ArrayList;
import java.util.List;

public class ArticleContentMutant implements I_ArticleContent {
  
  private List<Article> articles = new ArrayList<Article>();
  private List<ArticleTree> articleTrees = new ArrayList<ArticleTree>();
  private long lastTreeDate = 0L;
  private long lastArticleDate = 0L;
  
  private NiceDate niceDate = new NiceDate(
      ((AddocI18nConstants) GWT.create(AddocI18nConstants.class)).getDateTimeFormat());
  public ArticleContentMutant() {}
  
  public ArticleContentMutant(I_ArticleContent other) {
    List<I_Article> arts = other.getArticles();
    for (I_Article art: arts) {
      addArticle(art);
    }
    List<I_ArticleTree> trees = other.getArticleTrees();
    for (I_ArticleTree tree: trees) {
      addArticleTree(tree);
    }
  }
  
  public void addArticle(I_Article article) {
    Article a = new Article(article);
    if (a.getId() != articles.size()) {
      throw new IllegalArgumentException("The article id must match the index of the article.");
    }
    String artDate = article.getDate();
    long dateLong = niceDate.parse(artDate);
    if (lastArticleDate < dateLong) {
      lastArticleDate = dateLong;
    }
    articles.add(a);
  }
  
  public void addArticleTree(I_ArticleTree tree) {
    ArticleTree treeCopy = new ArticleTree(tree);
    String date = tree.getDate();
    
    long dateLong = niceDate.parse(date);
    if (dateLong <= lastTreeDate) {
      throw new IllegalArgumentException("The tree date must be greater than the previous tree.");
    }
    lastTreeDate = dateLong;
    articleTrees.add(treeCopy);
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_Content#getArticle(int)
   */
  @Override
  public Article getArticle(int id) {
    return articles.get(id);
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_Content#getLatestTree()
   */
  @Override
  public ArticleTree getLatestTree() {
    return articleTrees.get(getLatestTreeIndex());
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_Content#getLatestTreeIndex()
   */
  @Override
  public int getLatestTreeIndex() {
    return articleTrees.size() - 1;
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_Content#getTree(int)
   */
  @Override
  public ArticleTree getTree(int index) {
    return articleTrees.get(index);
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_Content#getTree(java.lang.String)
   */
  @Override
  public ArticleTree getTree(String date) {
    long dateLong = niceDate.parse(date);
    long lastTreeDate = 0L;
    if (articleTrees.size() == 1) {
      return articleTrees.get(0);
    }
    ArticleTree lastTree = null;
    for (ArticleTree tree: articleTrees) {
      String treeDate = tree.getDate();
      long treeDateLong = niceDate.parse(treeDate);
      if (lastTreeDate == 0L) {
        //do nothing
      } else {
        if (lastTreeDate <= dateLong && treeDateLong >= dateLong) {
          return tree;
        }
      }
      lastTreeDate = treeDateLong;
      lastTree = tree;
    }
    return lastTree;
  }

  public List<I_Article> getArticles() {
    return new ArrayList<I_Article>(articles);
  }

  public List<I_ArticleTree> getArticleTrees() {
    return new ArrayList<I_ArticleTree>(articleTrees);
  }
  
  public String getLastModifiedDate() {
    long toRet = lastArticleDate;
    if (toRet < lastTreeDate) {
      toRet = lastTreeDate;
    }
    return niceDate.format(toRet);
  }
}
