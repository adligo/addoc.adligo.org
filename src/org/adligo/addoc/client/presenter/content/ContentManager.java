package org.adligo.addoc.client.presenter.content;


import org.adligo.addoc.client.bi.I_Browser;
import org.adligo.addoc.client.bi.I_TextFileCallback;
import org.adligo.addoc.client.i18n.GWTCreateWrapper;
import org.adligo.addoc.client.i18n.LazyArticleBriefs;
import org.adligo.addoc.client.i18n.LazyArticleTrees;
import org.adligo.addoc.client.i18n.OneHundredArticleBriefs;
import org.adligo.addoc.client.i18n.TenArticleTrees;
import org.adligo.addoc.client.models.ArticleBuilder;
import org.adligo.addoc.client.models.ArticleTreesBuilder;
import org.adligo.addoc.client.models.I_AddocContent;
import org.adligo.addoc.client.models.I_Article;
import org.adligo.addoc.client.models.I_ArticleBrief;
import org.adligo.addoc.client.models.I_ArticleTree;
import org.adligo.addoc.client.models.IdRange;
import org.adligo.addoc.client.models.IdRangeBTreeLookup;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class ContentManager implements I_ContentManager, I_TextFileCallback {
  public static final String CONTENT_MANAGER_REQUIRES_A_ARTICLE_BASE_URL = "ContentManager requires a articleBaseUrl";

  public static final String CONTENT_MANAGER_REQUIRES_A_BROWSER_INSTANCE = "ContentManager requires a browser instance.";

  private String baseArticleUrl_;
  private int latestTree_;
  private int lastArticle_;
  private I_AddocContent addocContent_;
  private IdRangeBTreeLookup<GWTCreateWrapper<TenArticleTrees>> lazyArticleTrees_;
  private IdRangeBTreeLookup<GWTCreateWrapper<OneHundredArticleBriefs>> lazyArticleBriefs_;
  private IdRangeBTreeLookup<Boolean> lazyArticleTreesRequestsSent_;
  private Map<Integer, Boolean> lazyArticleBriefsRequestsSent_ = new HashMap<Integer,Boolean>();
  private Map<Integer, I_ArticleTree> articleTrees_ = new HashMap<Integer,I_ArticleTree>();
  private Map<Integer, I_ArticleBrief> articleBriefs_ = new HashMap<Integer,I_ArticleBrief>();
  private I_ArticleRequestor articleRequest_ = null;
  private I_ArticleBrief articleRequestBrief_ = null;
  private I_Browser browser_;
  private ArticleBriefsCallback articleBriefsCallback_;
  private TreesCallback treesCallback_;
  private ArticleTreesBuilder treeBuilder_ = new ArticleTreesBuilder();
  
  public ContentManager(I_Browser browser, String articleBaseUrl) {
    if (browser == null) {
      throw new IllegalArgumentException(CONTENT_MANAGER_REQUIRES_A_BROWSER_INSTANCE);
    }
    if (articleBaseUrl == null) {
      throw new IllegalArgumentException(CONTENT_MANAGER_REQUIRES_A_ARTICLE_BASE_URL);
    }
    browser_ = browser;
    baseArticleUrl_ = articleBaseUrl;
    treesCallback_ = new TreesCallback(articleTrees_);
    articleBriefsCallback_ = new ArticleBriefsCallback();
  }
  
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
    
    treesCallback_.setIds(ids);
    treesCallback_.setRequestor(requestor);
    treesCallback_.setTreeId(treeId);
    treesCallback_.setTreesClass(treesClass);
    treesCallback_.setTreeBuilder(treeBuilder_);
    browser_.runAsync(treesCallback_);
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
  public void requestArticle(I_ArticleBrief articleBrief, I_ArticleRequestor requestor) {
    if (articleRequestBrief_ != null) {
      requestor.onFailure(new IllegalStateException("Pending request for articles should not overlap."));
      return;
    }
    articleRequestBrief_ = articleBrief;
    articleRequest_ = requestor;
    int articleId = articleBrief.getId();
    browser_.doGet(baseArticleUrl_ + "/a" + articleId + ".txt", this);
  }

  public void setAddocContent(I_AddocContent addocContent) {
    this.addocContent_ = addocContent;
  }
  
  @SuppressWarnings("boxing")
  private void loadArticleBriefs(final GWTCreateWrapper<OneHundredArticleBriefs> briefsCreator,final IdRange range, final I_ArticleBriefRequestor requestor) {
    
    articleBriefsCallback_.setBriefsCreator(briefsCreator);
    articleBriefsCallback_.setRange(range);
    articleBriefsCallback_.setRequestor(requestor);
    lazyArticleBriefsRequestsSent_.put(range.getStart(), true);
    browser_.runAsync(articleBriefsCallback_);
  }

  public ArticleBriefsCallback getArticleBriefsCallback() {
    return articleBriefsCallback_;
  }
  
  public TreesCallback getTreesCallback() {
    return treesCallback_;
  }

  @Override
  public void onSuccess(String text) {
    ArticleBuilder ab = new ArticleBuilder();
    I_Article article = ab.build(articleRequestBrief_, text);
    articleRequestBrief_ = null;
    articleRequest_.onSuccess(article);
  }

  @Override
  public void onFailure(Throwable error) {
    articleRequestBrief_ = null;
    articleRequest_.onFailure(error);
  }

  public ArticleTreesBuilder getTreeBuilder() {
    return treeBuilder_;
  }

  public void setTreeBuilder(ArticleTreesBuilder treeBuilder) {
    this.treeBuilder_ = treeBuilder;
  }
}
