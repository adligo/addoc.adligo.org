package org.adligo.addoc.client.models;

import java.util.List;

public interface I_NumberedTree {

  public abstract List<Integer> getIds(int [] from);

  public abstract List<Integer> getTop();

}