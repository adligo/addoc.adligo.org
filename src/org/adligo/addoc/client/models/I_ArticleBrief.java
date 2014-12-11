package org.adligo.addoc.client.models;


public interface I_ArticleBrief {

  public abstract int getId();


  /**
   * This is the article that this article replaces,
   * this is done in a linked list pattern
   * so that the original article is not required 
   * to keep the footprint of addoc small.
   * @return
   */
  public abstract int getPreviousId();

  public abstract String getName();
}