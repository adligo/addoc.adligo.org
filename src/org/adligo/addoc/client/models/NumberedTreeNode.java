package org.adligo.addoc.client.models;

import java.util.Arrays;
import java.util.List;

public class NumberedTreeNode {
  private int [] parents_;
  
  public NumberedTreeNode(int [] parents) {
    parents_ = parents;
  }

  @SuppressWarnings("boxing")
  public NumberedTreeNode(List<Integer> parents) {
    parents_ = new int[parents.size()];
    for (int i = 0; i < parents_.length; i++) {
      parents_[i] = parents.get(i);
    }
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    for (int i = 0; i < parents_.length; i++) {
      result = prime * result + parents_[i];
    }
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    NumberedTreeNode other = (NumberedTreeNode) obj;
    if (other.parents_.length != parents_.length) {
      return false;
    }
    for (int i = 0; i < parents_.length; i++) {
      if (other.parents_[i] != parents_[i]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    return "NumberedTreeNode [parents_=" + Arrays.toString(parents_) + "]";
  }
  
  
}
