package org.adligo.addoc.client.presenter.content;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;

import org.adligo.addoc.client.i18n.AddocI18nConstants;
import org.adligo.addoc.client.i18n.GWTCreateWrapper;
import org.adligo.addoc.client.i18n.LazyArticleBriefs;
import org.adligo.addoc.client.i18n.LazyArticleTrees;
import org.adligo.addoc.client.i18n.OneHundredArticleBriefs;
import org.adligo.addoc.client.i18n.TenArticleTrees;
import org.adligo.addoc.client.models.ArticleBriefBuilder;
import org.adligo.addoc.client.models.ArticleTreesBuilder;
import org.adligo.addoc.client.models.I_AddocContent;
import org.adligo.addoc.client.models.I_ArticleBrief;
import org.adligo.addoc.client.models.I_ArticleTree;
import org.adligo.addoc.client.models.IdRange;
import org.adligo.addoc.client.models.IdRangeBTreeLookup;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This class does code splitting for the article trees
 * and article briefs, so that large addoc usage
 * doesn't download the entire history of articles
 * and trees before it presents things to the user.
 * 
 * This should always send immutable instances.
 * 
 * @author scott
 *
 */
public class ContentManager implements I_ContentManager {
  private static final AddocI18nConstants CONSTANTS = GWT.create(AddocI18nConstants.class);
  
  private String baseArticleUrl_ = CONSTANTS.getArticleRelativeUrl();
  private int latestTree_;
  private int lastArticle_;
  private I_AddocContent addocContent_;
  private IdRangeBTreeLookup<GWTCreateWrapper<TenArticleTrees>> lazyArticleTrees_;
  private IdRangeBTreeLookup<GWTCreateWrapper<OneHundredArticleBriefs>> lazyArticleBriefs_;
  private IdRangeBTreeLookup<Boolean> lazyArticleTreesRequestsSent_;
  private Map<Integer, Boolean> lazyArticleBriefsRequestsSent_ = new HashMap<Integer,Boolean>();
  private Map<Integer, I_ArticleTree> articleTrees_ = new HashMap<Integer,I_ArticleTree>();
  private Map<Integer, I_ArticleBrief> articleBriefs_ = new HashMap<Integer,I_ArticleBrief>();
  private Set<Integer> treeRequests_ = new HashSet<Integer>();
  private Set<Integer> articleRequests_ = new HashSet<Integer>();
  
  @SuppressWarnings({"rawtypes", "unchecked", "boxing"})
  public void setupArticleTrees(List<LazyArticleTrees> lazyTrees, int latestTree) {
    latestTree_ = latestTree;
    TreeMap<IdRange, GWTCreateWrapper<TenArticleTrees>> map = new TreeMap<IdRange, GWTCreateWrapper<TenArticleTrees>>();
    TreeMap<IdRange, Boolean> loadMap = new TreeMap<IdRange, Boolean>();
    TreeMap<IdRange, TenArticleTrees> loadedMap = new TreeMap<IdRange, TenArticleTrees>();
    for (LazyArticleTrees lt: lazyTrees) {
      IdRange range = lt.getIds();
      GWTCreateWrapper<TenArticleTrees> trees = (GWTCreateWrapper<TenArticleTrees>) lt.getTenArticleTrees();
      map.put(range, trees);
      loadMap.put(range, false);
      loadedMap.put(range, null);
    }
    lazyArticleTrees_ = new IdRangeBTreeLookup(map);
    lazyArticleTreesRequestsSent_ = new IdRangeBTreeLookup<Boolean>(loadMap);
  }

  @SuppressWarnings({"rawtypes", "unchecked", "boxing"})
  public void setupArticleBriefs(List<LazyArticleBriefs> lazyArticleBriefs, int lastArticle) {
    lastArticle_ = lastArticle;
    TreeMap<IdRange, GWTCreateWrapper<OneHundredArticleBriefs>> map = new TreeMap<IdRange, GWTCreateWrapper<OneHundredArticleBriefs>>();
    TreeMap<IdRange, Boolean> loadMap = new TreeMap<IdRange, Boolean>();
    TreeMap<IdRange, OneHundredArticleBriefs> loadedMap = new TreeMap<IdRange, OneHundredArticleBriefs>();
    for (LazyArticleBriefs briefs: lazyArticleBriefs) {
      IdRange range = briefs.getIds();
      GWTCreateWrapper<OneHundredArticleBriefs> hundredBriefs = (GWTCreateWrapper<OneHundredArticleBriefs>) briefs.getOneHundredArticleBriefs();
      map.put(range, hundredBriefs);
      loadMap.put(range, false);
      loadedMap.put(range, null);
    }
    lazyArticleBriefs_ = new IdRangeBTreeLookup(map);
  }

  @Override
  public String getLastModifiedDate() {
    return CONSTANTS.getLastModified();
  }


  @Override
  public int getLatestTree() {
    return latestTree_;
  }


  @Override
  public I_AddocContent getAddocContent() {
    return addocContent_;
  }

  @SuppressWarnings("boxing")
  @Override
  public void requestTree(final int treeId, final I_ArticleTreeRequestor requestor) {
    I_ArticleTree tree = articleTrees_.get(treeId);
    if (tree != null) {
      requestor.onSuccess(tree);
      return;
    }
    final IdRange ids = lazyArticleTrees_.getIds(treeId);
    final GWTCreateWrapper<TenArticleTrees> treesClass = lazyArticleTrees_.get(treeId);
    
    GWT.runAsync(new RunAsyncCallback() {
      public void onFailure(Throwable caught) {
        requestor.onFailure(caught);
      }

      public void onSuccess() {
        TenArticleTrees trees = treesClass.create();
        int end = ids.getEnd();
        if (end > latestTree_) {
          end = latestTree_;
        }
        Map<Integer,I_ArticleTree> treeInstances = ArticleTreesBuilder.buildTrees(trees, ids.getStart(), end);
        articleTrees_.putAll(treeInstances);
        
        I_ArticleTree tree = articleTrees_.get(treeId);
        if (tree != null) {
          requestor.onSuccess(tree);
          return;
        }
      }
    });
  }

  /**
   * you may not get what you think you will when calling this method
   * @param articleIds the first articleId of each section ie (0,100,200) will load 0-299.
   */
  @SuppressWarnings("boxing")
  @Override
  public void requestArticleBriefs(Collection<Integer> articleIds, final I_ArticleBriefRequestor requestor) {
    SortedSet<Integer> sortedIds = new TreeSet<Integer>(articleIds);
    Iterator<Integer> ids = sortedIds.iterator();
    
    Integer lastStart = 0;
    Integer lastEnd = 99;
    while (ids.hasNext()) {
      Integer id = ids.next();
      if (lastStart <= id && id <= lastEnd) {
        if (lastStart == 0) {
          IdRange range = lazyArticleBriefs_.getIds(0);
          GWTCreateWrapper<OneHundredArticleBriefs> articleBriefs = lazyArticleBriefs_.get(0);
          loadArticleBriefs(articleBriefs, range, requestor);
          lastStart = 100;
          lastEnd = 199;
        }
        //do nothing loads happen on the first id in the sequence
      } else if (id > lastEnd) {
        String hundreds = new String(""+ id);
        hundreds = hundreds.substring(0, hundreds.length() - 2) + "00";
        Integer start = new Integer(hundreds);
        lastStart = start;
        lastEnd = start + 99;
        
        IdRange range = lazyArticleBriefs_.getIds(lastStart);
        GWTCreateWrapper<OneHundredArticleBriefs> articleBriefs = lazyArticleBriefs_.get(lastStart);
        loadArticleBriefs(articleBriefs, range, requestor);
      }
    }
  }
  
  

  @Override
  public void requestArticle(int articleId,I_ArticleRequestor requestor) {
    // TODO Auto-generated method stub
    
  }

  public void setAddocContent(I_AddocContent addocContent) {
    this.addocContent_ = addocContent;
  }
  
  private void loadArticleBriefs(final GWTCreateWrapper<OneHundredArticleBriefs> briefsCreator,final IdRange range, final I_ArticleBriefRequestor requestor) {
    GWT.runAsync(new RunAsyncCallback() {
      public void onFailure(Throwable caught) {
        requestor.onFailure(caught);
      }

      public void onSuccess() {
        int start = range.getStart();
        int end = range.getEnd();
        lazyArticleBriefsRequestsSent_.put(range.getStart(), true);
        
        OneHundredArticleBriefs briefs = briefsCreator.create();
        List<I_ArticleBrief> articleBriefs =  ArticleBriefBuilder.buildBriefs(briefs, start, end);
        requestor.onSuccess(articleBriefs);
      }
    });
  }
}
