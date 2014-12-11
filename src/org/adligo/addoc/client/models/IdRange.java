package org.adligo.addoc.client.models;

public class IdRange implements Comparable<IdRange> {
  private int start_;
  private int end_;
  
  public IdRange(int start, int end) {
    start_ = start;
    end_ = end;
  }

  public int getStart() {
    return start_;
  }

  public int getEnd() {
    return end_;
  }
  
  public boolean contains(int p) {
    if (p >= start_ && p <= end_) {
      return true;
    }
    return false;
  }
  
  public int getRange() {
    return end_ - start_ + 1;
  }

  @Override
  public int compareTo(IdRange o) {
    if (this == o) {
      return 0;
    }
    return new Integer(start_).compareTo(o.start_);
  }
  
  public int compareTo(Integer o) {
    if (contains(o)) {
      return 0;
    }
    if (start_ > o) {
      double diff = start_ - o;
      double rangeDouble = getRange();
      diff = diff/rangeDouble;
      return (int) diff * -1;
    } else  {
      double diff = o - end_;
      double rangeDouble = getRange();
      diff = diff/rangeDouble;
      return (int) diff;
    }
  }
}
