package org.adligo.addoc.client.models;


public class Article  implements I_Article {
  private ArticleMutant mutant = null;
  
  public Article(I_Article a) {
    mutant = new ArticleMutant(a);
  }

  public int getId() {
    return mutant.getId();
  }

  public String getContent() {
    return mutant.getContent();
  }

  public int getHeight() {
    return mutant.getHeight();
  }

  public String getDate() {
    return mutant.getDate();
  }

  public int getPreviousId() {
    return mutant.getPreviousId();
  }

  public String getName() {
    return mutant.getName();
  }

}
