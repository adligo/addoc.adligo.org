package org.adligo.addoc.client.models;


public class ArticleBrief implements I_ArticleBrief {
  private ArticleBriefMutant mutant = null;
  
  public ArticleBrief(I_ArticleBrief a) {
    mutant = new ArticleBriefMutant(a);
  }

  public int getId() {
    return mutant.getId();
  }

  public int getPreviousId() {
    return mutant.getPreviousId();
  }

  public String getName() {
    return mutant.getName();
  }

}
