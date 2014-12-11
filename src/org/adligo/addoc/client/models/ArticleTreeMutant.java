package org.adligo.addoc.client.models;

import java.util.List;

public class ArticleTreeMutant implements I_ArticleTree {
  private String date;
  private I_NumberedTree numberedTree;
  private int id;
  
  public ArticleTreeMutant() {}
  
  public ArticleTreeMutant(I_ArticleTree other) {
    setDate(other.getDate());
    numberedTree = other.getNumberedTree();
  }
  
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.shared.models.I_ArticleTree#getDate()
   */
  @Override
  public String getDate() {
    return date;
  }
  
  public void setDate(String date) {
    if (date == null) {
      throw new IllegalArgumentException("Null article tree dates are not allowed.");
    }
    this.date = date;
  }

  @Override
  public List<Integer> getIds(int [] from) {
    return numberedTree.getIds(from);
  }

  @Override
  public List<Integer> getTop() {
    return numberedTree.getTop();
  }

  @Override
  public I_NumberedTree getNumberedTree() {
    return numberedTree;
  }

  public void setNumberedTree(I_NumberedTree numberedTree) {
    this.numberedTree = numberedTree;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
