package org.adligo.addoc.client.models;

import java.util.Collection;
import java.util.List;

/**
 * a caching implementation
 * @author scott
 *
 */
public interface I_ContentCache {

  public abstract void addArticleBreif(int treeId, I_ArticleBrief article);

  public abstract void removeCachedArticleIds(Collection<Integer> articleIds);

  public abstract I_ArticleBrief getArticleBrief(int id);

  /**
   * call this method when sending async request 
   * to keep the footprint small
   * @paaram treeIds to keep in the cache
   * @param minArticleBriefs
   * @param millisecondsPast
   */
  public abstract void reduceArticleCache(List<Integer> treeIds, int minArticleBriefs, int millisecondsPast);

}