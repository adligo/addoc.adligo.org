package org.adligo.addoc.client.models;

import java.util.List;

public class Article implements I_Article {
  private ArticleMutant mutant = null;
  
  public Article(I_Article a) {
    mutant = new ArticleMutant(a);
  }

  public int getId() {
    return mutant.getId();
  }

  public String getDate() {
    return mutant.getDate();
  }

  public int getOriginalId() {
    return mutant.getOriginalId();
  }

  public String getName() {
    return mutant.getName();
  }

  public String getContent() {
    return mutant.getContent();
  }

  public boolean hasRevisions() {
    return mutant.hasRevisions();
  }

  public List<I_Article> getRevisions() {
    return mutant.getRevisions();
  }
}
