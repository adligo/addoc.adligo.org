package org.adligo.addoc.client.models;


public interface I_Article extends I_ArticleBrief {
  public int getHeight();

  public String getContent();
  

  public abstract String getDate();
}