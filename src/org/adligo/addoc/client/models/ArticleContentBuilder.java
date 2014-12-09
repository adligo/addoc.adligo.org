package org.adligo.addoc.client.models;

import com.google.gwt.i18n.client.ConstantsWithLookup;

import org.apache.commons.el.OrOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;

public class ArticleContentBuilder {
  /**
   * Note the index of the article is
   */
  private List<ArticleMutant> articles = new ArrayList<ArticleMutant>();
  private List<ArticleTreeMutant> trees = new ArrayList<ArticleTreeMutant>();
  
  public void addArticles(ConstantsWithLookup lookup, int startId, int endId) {
    for (int i = startId; i <= endId; i++) {
      addArticle(lookup, i + startId);
    }
  }
  
  public void addArticle(ConstantsWithLookup lookup, int id) {
    ArticleMutant am = new ArticleMutant();
    String date = lookup.getString("getA" + id + "Date");
    am.setDate(date);
    String name = lookup.getString("getA" + id + "Name");
    am.setName(name);
    
    am.setId(id);
    am.setContent(lookup.getString("getA" + id + "Content"));
    
      String originalId = lookup.getString("getA" + id + "Original");
      if (originalId.trim().length() != 0) {
        int oid = new Integer(originalId);
        am.setOriginalId(oid);
        ArticleMutant original = articles.get(oid);
        original.addRevision(am);
      }
     
    
    articles.add(am);
    ArticleMutant testId = articles.get(id);
    if (testId != am) {
      throw new IllegalArgumentException("Articles must be added to this instance in "
          + "order so their id's match with the list index.");
    }
  }
  
  public void addTrees(ConstantsWithLookup lookup, int startId, int endId) {
    for (int i = startId; i <= endId; i++) {
      addTree(lookup, i);
    }
  }
  
  public void addTree(ConstantsWithLookup lookup, int id) {
    ArticleTreeMutant tree = new ArticleTreeMutant();
    String date = lookup.getString("getTree" + id + "Date");
    tree.setDate(date);
    String articleTree = lookup.getString("getTree" + id);
    I_NumberedTree nt = new NumberedTree(articleTree);
    tree.setNumberedTree(nt);
    trees.add(tree);
  }
  
  public ArticleContent toContent() {
    ArticleContentMutant acm = new ArticleContentMutant();
    for (ArticleMutant am: articles) {
      acm.addArticle(am);
    }
    for (ArticleTreeMutant tree: trees) {
      acm.addArticleTree(tree);
    }
    return new ArticleContent(acm);
  }
}
