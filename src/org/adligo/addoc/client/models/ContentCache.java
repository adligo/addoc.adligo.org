package org.adligo.addoc.client.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/** This class is a fairly simple single threaded cache
 * which does stop the world style garbage collection
 * by removing references to instances, so that the javascript
 * garbage collector can reclaim or reduce memory.
 * 
 * Things are removed when they reach a threshold
 * and a time limit has passed, when a presenter calls
 * reduceCache();
 * 
 * @author scott
 *
 */
public class ContentCache implements I_ContentCache {
  private Map<Integer,I_ArticleBrief> articleCache_ = new HashMap<Integer,I_ArticleBrief>();
  private TreeMap<Integer,Set<Integer>> articleToTree_ = new TreeMap<Integer,Set<Integer>>();
  private TreeMap<Long,Integer> articleAddTimes_ = new TreeMap<Long,Integer>();
  private I_Timepiece timepiece_;
  
  public ContentCache(I_Timepiece timepiece) {
    timepiece_ = timepiece;
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_ContentCache#addArticleBreif(org.adligo.addoc.client.models.I_ArticleBrief)
   */
  @Override
  @SuppressWarnings("boxing")
  public void addArticleBreif(int treeId, I_ArticleBrief article) {
    int id = article.getId();
    Set<Integer> treeIds = articleToTree_.get(id);
    if (treeIds == null) {
      treeIds = new HashSet<Integer>();
      articleToTree_.put(id, treeIds);
    }
    treeIds.add(treeId);
    articleAddTimes_.put(timepiece_.getTime(), id);
    articleCache_.put(id, article);
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_ContentCache#removeCachedArticleIds(java.util.Collection)
   */
  @Override
  public void removeCachedArticleIds(Collection<Integer> articleIds) {
    articleIds.removeAll(articleCache_.keySet());
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_ContentCache#getArticleBrief(int)
   */
  @Override
  public I_ArticleBrief getArticleBrief(int id) {
    return articleCache_.get(id);
  }
  
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_ContentCache#reduceArticleCache(int, int)
   */
  @Override
  public void reduceArticleCache(List<Integer> treeIds, int minArticleBriefs, int millisecondsPast) {
    if (articleCache_.size() >= minArticleBriefs) {
      Long time = timepiece_.getTime();
      Set<Long> addTimes = articleAddTimes_.keySet();
      Iterator<Long> adIt = addTimes.iterator();
      
      while (adIt.hasNext()) {
        long addtime = adIt.next();
        long passed = time - addtime;
        if (millisecondsPast >= passed) {
          
          Integer articleId = articleAddTimes_.get(addtime);
          Set<Integer> articleTreeIds = articleToTree_.get(articleId);
          boolean contained = false;
          for (Integer id: treeIds) {
            if (articleTreeIds.contains(id)) {
              contained = true;
            }
          }
          if (!contained) {
            adIt.remove();
            articleToTree_.remove(articleId);
            articleCache_.remove(articleId);
          }
        } else {
          break;
        }
      }
    }
  }
}
