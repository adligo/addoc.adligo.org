package org.adligo.addoc.client.models;


public class ArticleMutant extends ArticleBriefMutant implements I_Article {
  private int height;
  private String content;
  /**
   * when it was changed
   */
  private String date;
  
  public ArticleMutant() {}
  
  public ArticleMutant(I_ArticleBrief a) {
    super(a);
  }
  
  public ArticleMutant(I_Article a) {
    super(a);
    setDate(a.getDate());
    setName(a.getName());
    setId(a.getId());
    setPreviousId(a.getPreviousId());
    setContent(a.getContent());
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    if (content == null) {
      throw new IllegalArgumentException("Article Content may not be null.");
    }
    this.content = content;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    if (height < 100) {
      throw new IllegalArgumentException("height must be 100 or greater");
    }
    this.height = height;
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.shared.models.I_Article#getDate)
   */
  @Override
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    if (date == null) {
      throw new IllegalArgumentException("Article Date may not be null.");
    }
    this.date = date;
  }
}
