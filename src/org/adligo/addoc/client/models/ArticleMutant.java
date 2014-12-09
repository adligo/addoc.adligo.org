package org.adligo.addoc.client.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
  private String date;
  /**
   * the original article, this article is the new replacement for
   */
  private int originalId = -1;
  /**
   * the i18n/marked up text for the content
   */
  private String content;
  /**
   * 
   */
  private List<ArticleMutant> revisions;
  
  public ArticleMutant() {}
  
  public ArticleMutant(I_Article a) {
    setDate(a.getDate());
    setName(a.getName());
    setId(a.getId());
    setOriginalId(a.getOriginalId());
    setContent(a.getContent());
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
    if (name == null) {
      throw new IllegalArgumentException("Article Names may not be null.");
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    if (content == null) {
      throw new IllegalArgumentException("Article Content may not be null.");
    }
    this.content = content;
  }
  
  public boolean hasRevisions() {
    if (revisions == null) {
      return false;
    }
    return true;
  }
  
  public List<I_Article> getRevisions() {
    if (revisions == null) {
      return Collections.emptyList();
    }
    return new ArrayList<I_Article>(revisions);
  }
  
  public void addRevision(ArticleMutant am) {
    if (revisions == null) {
      revisions = new ArrayList<ArticleMutant>();
    }
    revisions.add(am);
  }
  public void addRevision(I_Article am) {
    addRevision(new ArticleMutant(am));
  }
}
