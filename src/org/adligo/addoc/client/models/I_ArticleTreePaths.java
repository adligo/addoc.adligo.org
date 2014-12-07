package org.adligo.addoc.client.models;

import java.util.List;

public interface I_ArticleTreePaths {
  public List<String> getAllOrdered();
  
  public List<String> getTop();

  public List<String> getSub(String parentPath);

}