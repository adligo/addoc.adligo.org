package org.adligo.addoc.client.models;


public class AddocContentMutant implements I_AddocContent {
  private I_ArticleContent articleContent;
  private I_SimplePanelContent titleImage;
  private I_SimplePanelContent headerAd;
  private I_SimplePanelContent [] rightAds;
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_AddocContent#getArticleContent()
   */
  @Override
  public I_ArticleContent getArticleContent() {
    return articleContent;
  }
  public void setArticleContent(I_ArticleContent articleContent) {
    this.articleContent = articleContent;
  }
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_AddocContent#getHeaderImage()
   */
  @Override
  public I_SimplePanelContent getTitleImage() {
    return titleImage;
  }
  public void setTitleImage(I_SimplePanelContent headerImage) {
    this.titleImage = headerImage;
  }
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_AddocContent#getHeaderAd()
   */
  @Override
  public I_SimplePanelContent getHeaderAd() {
    return headerAd;
  }
  public void setHeaderAd(I_SimplePanelContent headerAd) {
    this.headerAd = headerAd;
  }
  public I_SimplePanelContent[] getRightAds() {
    return rightAds;
  }
  public void setRightAds(I_SimplePanelContent[] rightAds) {
    this.rightAds = rightAds;
  }
  
}
