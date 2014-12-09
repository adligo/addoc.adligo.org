package org.adligo.addoc.client.models;

import java.util.List;

public interface I_Article {

  public abstract int getId();

  public abstract String getDate();

  public abstract int getOriginalId();

  public abstract String getName();

  public String getContent();
  
  public boolean hasRevisions();
  
  public List<I_Article> getRevisions();
}