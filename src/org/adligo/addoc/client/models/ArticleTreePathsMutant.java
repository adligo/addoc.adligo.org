package org.adligo.addoc.client.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleTreePathsMutant implements I_ArticleTreePaths {
  private List<String> pathOrder = new ArrayList<String>();
  private List<String> top = null;
  private Map<String,List<String>> subs = new HashMap<String,List<String>>();
  
  public void addPath(String path) {
    pathOrder.add(path);
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.shared.models.I_ArticleTreePaths#getTop()
   */
  @Override
  public List<String> getTop() {
    if (top == null) {
      top = new ArrayList<String>();
      for (String path: pathOrder) {
        if (path.indexOf("/") == -1) {
            top.add(path);
        }
      }
    }
    return top;
  }
    
    
  /* (non-Javadoc)
   * @see org.adligo.addoc.shared.models.I_ArticleTreePaths#getSub(java.lang.String)
   */
  @Override
  public List<String> getSub(String parentPath) {
    List<String> toRet = subs.get(parentPath);
    
    if (toRet == null) {
      toRet = new ArrayList<String>();
      for (String path: pathOrder) {
        if (path.indexOf(parentPath) == 0) {
          int nextSlash = path.indexOf("/", parentPath.length());
          if (nextSlash == -1) {
            String toAdd = path.substring(parentPath.length(), path.length() - 1);
            toRet.add(toAdd);
          } else {
            String toAdd = path.substring(parentPath.length(), nextSlash);
            toRet.add(toAdd);
          }
        }
      }
      subs.put(parentPath, toRet);
    }
    return toRet;
  }

  @Override
  public List<String> getAllOrdered() {
    return pathOrder;
  }

}
