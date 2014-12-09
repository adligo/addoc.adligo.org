package org.adligo.addoc.client.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberedTree implements I_NumberedTree {
  private Map<NumberedTreeNode,List<Integer>> idMap = new HashMap<NumberedTreeNode,List<Integer>>();
  
  public NumberedTree(String asString) {
    char [] chars = asString.toCharArray();
    List<Integer> parents = new ArrayList<Integer>();
    NumberedTreeNode parentNode = null;
    StringBuilder nextId = new StringBuilder();
    List<Integer> currentList = new ArrayList<Integer>();
    boolean inHeader = false;
    
    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      if (c == '&') {
        currentList.add(new Integer(nextId.toString()));
        if (parentNode == null) {
          idMap.put(new NumberedTreeNode(new int[] {-1}), Collections.unmodifiableList(currentList));
        } else {
          idMap.put(parentNode, Collections.unmodifiableList(currentList));
        }
        currentList = new ArrayList<Integer>();
        nextId = new StringBuilder();
        inHeader = true;
      } else if (c == ';') {
        parents.add(new Integer(nextId.toString()));
        parentNode = new NumberedTreeNode(parents);
        parents.clear();
        nextId = new StringBuilder();
        inHeader = false;
      } else if (c == ',') {
        if (inHeader) {
          parents.add(new Integer(nextId.toString()));
        } else {
          currentList.add(new Integer(nextId.toString()));
        }
        nextId = new StringBuilder();
      } else if (c == '/') {
      } else {
        nextId.append(c);
      }
    }
    
    String nextIdString = nextId.toString();
    if (nextIdString.trim().length() >= 1) {
      currentList.add(new Integer(nextId.toString()));
      if (parentNode == null) {
        idMap.put(new NumberedTreeNode(new int[] {-1}), Collections.unmodifiableList(currentList));
      } else {
        idMap.put(parentNode, Collections.unmodifiableList(currentList));
      }
    }
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_NumberedTree#getIds(int)
   */
  @Override
  @SuppressWarnings("boxing")
  public List<Integer> getIds(int [] from) {
    return idMap.get(new NumberedTreeNode(from));
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_NumberedTree#getTop()
   */
  @Override
  @SuppressWarnings("boxing")
  public List<Integer> getTop() {
    return idMap.get(new NumberedTreeNode(new int[] {-1}));
  }
}
