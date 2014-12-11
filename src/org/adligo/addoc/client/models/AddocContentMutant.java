package org.adligo.addoc.client.models;


public class AddocContentMutant implements I_AddocContent {
  private I_SimplePanelContent titleImage_;
  private I_SimplePanelContent headerAd_;
  private I_SimplePanelContent [] rightAds_;
  private I_SimplePanelContent [] leftAds_;
  private I_ContentCache contentCache_;

  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_AddocContent#getHeaderImage()
   */
  @Override
  public I_SimplePanelContent getTitleImage() {
    return titleImage_;
  }
  public void setTitleImage(I_SimplePanelContent headerImage) {
    this.titleImage_ = headerImage;
  }
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_AddocContent#getHeaderAd()
   */
  @Override
  public I_SimplePanelContent getHeaderAd() {
    return headerAd_;
  }
  public void setHeaderAd(I_SimplePanelContent headerAd) {
    this.headerAd_ = headerAd;
  }
  public I_SimplePanelContent[] getRightAds() {
    return rightAds_;
  }
  public void setRightAds(I_SimplePanelContent[] rightAds) {
    this.rightAds_ = rightAds;
  }
  public I_SimplePanelContent[] getLeftAds() {
    return leftAds_;
  }
  public void setLeftAds(I_SimplePanelContent[] leftAds) {
    this.leftAds_ = leftAds;
  }
  public I_ContentCache getContentCache() {
    return contentCache_;
  }
  public void setContentCache(I_ContentCache contentCache) {
    this.contentCache_ = contentCache;
  }  
}
