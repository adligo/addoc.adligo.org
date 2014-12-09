package org.adligo.addoc.client.models;

import java.util.List;

public class ArticleTree implements I_ArticleTree {
  private ArticleTreeMutant mutant;
  
  public ArticleTree(I_ArticleTree other) {
    mutant = new ArticleTreeMutant(other);
  }

  public String getDate() {
    return mutant.getDate();
  }

  public List<Integer> getIds(int [] from) {
    return mutant.getIds(from);
  }

  public List<Integer> getTop() {
    return mutant.getTop();
  }

  public I_NumberedTree getNumberedTree() {
    return mutant.getNumberedTree();
  }
  
  

}
