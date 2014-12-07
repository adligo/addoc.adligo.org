package org.adligo.addoc.client.models;

public class Article implements I_Article {
  private ArticleMutant mutant = null;
  
  public Article(I_Article a) {
    mutant = new ArticleMutant(a);
  }

  public int getId() {
    return mutant.getId();
  }

  public String getChanged() {
    return mutant.getChanged();
  }

  public int getOriginalId() {
    return mutant.getOriginalId();
  }

  public String getName() {
    return mutant.getName();
  }
}
