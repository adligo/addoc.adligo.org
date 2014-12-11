package org.adligo.addoc.client.models;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class IdRangeBTreeLookup <T> {
  private SortedMap<IdRange, T> map_;
  
  public IdRangeBTreeLookup(SortedMap<IdRange, T> map) {
    map_ = map;
  }
  
  public void put(IdRange key, T value) {
    map_.put(key, value);
  }
  
  public T get(int id) {
    IdRange range = getIds(id);
    return map_.get(range);
  }
  

  public IdRange getIds(int id) {
    Set<IdRange> ranges = map_.keySet();
    double rangesSize = ranges.size();
    double middle = rangesSize/2;
    
    int middleInt = (int) middle;
    Iterator<IdRange> it = ranges.iterator();
    IdRange beforeKey = null;
    IdRange key = null;
    IdRange afterKey = null;
    for (int i = 0; i <= middleInt + 1; i++) {
      if (it.hasNext()) {
        IdRange next = it.next();
        if (i == middleInt - 1) {
          beforeKey = next;
        }
        if (i == middleInt) {
          key = next;
        }
        if (i == middleInt + 1) {
          afterKey = next;
        }
      }
    }
    int c = key.compareTo(id);
    if (c == 0) {
      return key;
    } else if (c > 0) {
      return afterKey;
    } else {
      return beforeKey;
    }
  }
}
