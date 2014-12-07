package org.adligo.addoc.client.models;

public class ArticleMutant implements I_Article {
  /**
   * The name of the article,
   * may not be unique if a article is 
   * replaced with another article.
   */
  private String name;
  /**
   * a unique identifier
   */
  private int id;
  /**
   * when it was changed
   */
  private String changed;
  /**
   * the original article, this article is the new replacement for
   */
  private int originalId = -1;
  
  public ArticleMutant(I_Article a) {
    setChanged(a.getChanged());
    setName(a.getName());
    setId(a.getId());
    setOriginalId(a.getOriginalId());
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.shared.models.I_Article#getId()
   */
  @Override
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  /* (non-Javadoc)
   * @see org.adligo.addoc.shared.models.I_Article#getChanged()
   */
  @Override
  public String getChanged() {
    return changed;
  }
  public void setChanged(String changed) {
    this.changed = changed;
  }
  /* (non-Javadoc)
   * @see org.adligo.addoc.shared.models.I_Article#getOriginalId()
   */
  @Override
  public int getOriginalId() {
    return originalId;
  }
  public void setOriginalId(int originalId) {
    this.originalId = originalId;
  }
  /* (non-Javadoc)
   * @see org.adligo.addoc.shared.models.I_Article#getName()
   */
  @Override
  public String getName() {
    return name;
  }
  public void setName(String name) {
    if (name.indexOf("/") != -1) {
      throw new IllegalArgumentException("Article Names may not contain a /.");
    }
    this.name = name;
  }
  
  
}
