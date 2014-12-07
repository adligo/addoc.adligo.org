package org.adligo.addoc.client.models;

public interface I_SimplePanelContent {

  public abstract int getWidth();

  public abstract int getHeight();
  /**
   * The relative url to a the content from
   * the module html file.  This may contain html file which contains
   * the panel content this could be Ad javascript (i.e.
   * adds/bidvertiser.com/leaderboard.html).  
   * This can also be a url of a image.
   */
  public abstract String getUrl();

}