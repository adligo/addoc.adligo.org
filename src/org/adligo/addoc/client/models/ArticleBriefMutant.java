package org.adligo.addoc.client.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleBriefMutant implements I_ArticleBrief {
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
   * the original article, this article is the new replacement for
   */
  private int previousId = -1;
  
  public ArticleBriefMutant() {}
  
  public ArticleBriefMutant(I_ArticleBrief a) {
    setName(a.getName());
    setId(a.getId());
    setPreviousId(a.getPreviousId());
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
   * @see org.adligo.addoc.shared.models.I_Article#getPreviousId()
   */
  @Override
  public int getPreviousId() {
    return previousId;
  }
  public void setPreviousId(int previousId) {
    this.previousId = previousId;
  }
  /* (non-Javadoc)
   * @see org.adligo.addoc.shared.models.I_Article#getName()
   */
  @Override
  public String getName() {
    return name;
  }
  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Article Names may not be null.");
    }
    if (name.indexOf("#") != -1) {
      throw new IllegalArgumentException("Article Names may not contain a &.");
    }
    if (name.indexOf("&") != -1) {
      throw new IllegalArgumentException("Article Names may not contain a &.");
    }
    if (name.indexOf(";") != -1) {
      throw new IllegalArgumentException("Article Names may not contain a ;.");
    }
    if (name.indexOf("=") != -1) {
      throw new IllegalArgumentException("Article Names may not contain a =.");
    }
    if (name.indexOf("/") != -1) {
      throw new IllegalArgumentException("Article Names may not contain a /.");
    }
    this.name = name;
  }
}
