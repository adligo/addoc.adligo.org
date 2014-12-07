package org.adligo.addoc.client.ui;

public class Dimension implements I_Dimension {
  private int dim_;
  /**
   * if true the dim is a percentage
   * otherwise it is a px
   */
  private boolean isPct_;
  
  public Dimension(int dim) {
    dim_ = dim;
  }
  
  public Dimension(int dim, boolean isPct) {
    dim_ = dim;
    isPct_ = isPct;
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.ui.I_Dimension#getDimension()
   */
  @Override
  public String getDimension() {
    if (isPct_) {
      return "" + dim_ + "%";
    } else {
      return "" + dim_ + "px";
    }
  }

  /* (non-Javadoc)
   * @see org.adligo.addoc.client.ui.I_Dimension#getDim()
   */
  @Override
  public int getDim() {
    return dim_;
  }

  /* (non-Javadoc)
   * @see org.adligo.addoc.client.ui.I_Dimension#isPct()
   */
  @Override
  public boolean isPct() {
    return isPct_;
  }
}
