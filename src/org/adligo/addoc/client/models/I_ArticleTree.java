package org.adligo.addoc.client.models;


public interface I_ArticleTree extends I_NumberedTree {
  public abstract String getDate();
  public I_NumberedTree getNumberedTree();
  public int getId();
}