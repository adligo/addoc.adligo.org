package org.adligo.addoc.client.presenter;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;

import org.adligo.addoc.client.i18n.AddocI18nConstants;
import org.adligo.addoc.client.i18n.SizeParser;
import org.adligo.addoc.client.models.I_AddocContent;
import org.adligo.addoc.client.models.I_Article;
import org.adligo.addoc.client.models.I_ArticleBrief;
import org.adligo.addoc.client.models.I_ArticleTree;
import org.adligo.addoc.client.models.I_ContentCache;
import org.adligo.addoc.client.presenter.content.I_ArticleBriefRequestor;
import org.adligo.addoc.client.presenter.content.I_ArticleRequestor;
import org.adligo.addoc.client.presenter.content.I_ArticleTreeRequestor;
import org.adligo.addoc.client.presenter.content.I_ContentManager;
import org.adligo.addoc.client.ui.Dimension;
import org.adligo.addoc.client.ui.I_AdView;
import org.adligo.addoc.client.ui.I_ArticleTreeView;
import org.adligo.addoc.client.ui.I_ArticleView;
import org.adligo.addoc.client.ui.I_DialogView;
import org.adligo.addoc.client.ui.I_MenuView;
import org.adligo.addoc.client.ui.events.AddocEvent;
import org.adligo.addoc.client.ui.events.I_AddocHandler;
import org.adligo.addoc.client.view.dialog.DialogView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticlePresenter implements I_AddocHandler, I_ArticleTreeRequestor, I_ArticleBriefRequestor, I_ArticleRequestor {
  private AddocI18nConstants CONSTANTS = GWT.create(AddocI18nConstants.class);
  
  private I_MenuView menuView_;
  private I_ArticleTreeView articleTreeView_;
  private I_ArticleView articleView_;
  private I_ContentManager contentManager_;
  private I_ContentCache cache_;
  
  private Integer requestedTreeId_;
  private Set<Integer> requestedArticleBriefs_ = new HashSet<Integer>();
  private Set<Integer> requestedArticles_ = new HashSet<Integer>();
  
  private I_ArticleTree currentTree_;
  private I_Article currentArticle_;
  private I_DialogView dialogView_;
  private List<I_AdView> adViews_ = new ArrayList<I_AdView>();
  
  public I_MenuView getMenuView() {
    return menuView_;
  }
  public void setMenuView(I_MenuView menuView) {
    menuView_ = menuView;
    menuView_.setHandler(this);
  }
  public I_ArticleTreeView getArticleTreeView() {
    return articleTreeView_;
  }
  public void setArticleTreeView(I_ArticleTreeView articleTreeView) {
    articleTreeView_ = articleTreeView;
    articleTreeView_.setHandler(this);
  }
  public I_ArticleView getArticleView() {
    return articleView_;
  }
  public void setArticleView(I_ArticleView articleView) {
    articleView_ = articleView;
  }
  public I_ContentManager getContentManager() {
    return contentManager_;
  }
  public void setContentManager(I_ContentManager contentManager) {
    contentManager_ = contentManager;
  }
  
  public void start(String browserUrl) {
    I_AddocContent addocContent = contentManager_.getAddocContent();
    cache_ = addocContent.getContentCache();
    
    int hi = browserUrl.indexOf("#");
    
    if (hi != -1) {
      String history = browserUrl.substring(hi + 1, browserUrl.length());
      dialogView_.setCloseButtonVisible();
      dialogView_.setHeight("100px");
      dialogView_.setWidth("400px");
      dialogView_.setMessageText("ToDo History support for incoming history links is not availiable yet.", true);
      dialogView_.show();
    } else {
      requestedTreeId_ = contentManager_.getLatestTree();
      //note when I get to change tree from a event this needs to occur
      //cache_.reduceArticleCache(Collections.singleton(requestedTreeId_), 10000, millisecondsPast);
      contentManager_.requestTree(requestedTreeId_, this);
      
    }
    /*
    currentTree_ = content_.getLatestTree();
    articleTreeView_.setDateModified(currentTree_.getDate());
    
    List<Integer> tops =currentTree_.getTop();
    for (Integer top: tops) {
      I_Article article = content_.getArticle(top);
      String name = article.getName();
      articleTreeView_.addNode(new String[] {name});
      List<String> parents = new ArrayList<String>();
      parents.add(name);
      addChildNodes(new int[] {top}, parents);
    }
    */
  }

  
  @Override
  public void onSimpleEvent(AddocEvent event) {
    switch (event) {
      case IndexClick:
          articleTreeView_.show();
        break;
      default:
        Window.alert("non handled event " + event);
    }
  }
  @Override
  public void onArticleClick(String[] nodePath) {
    String topNode = nodePath[0];
    /*
    I_Article topArticle = null;
    List<Integer> ids = currentTree_.getTop();
    for (int i = 0; i < ids.size(); i++) {
      int id = ids.get(i);
      I_Article art = content_.getArticle(id);
      if (topNode.equals(art.getName())) {
        topArticle = art;
        break;
      }
    }
    if (topArticle != null) {
      if (nodePath.length >= 2) {
        
      } else {
        
      }
    }
    */
  }
  
  private void getArticle(I_Article parent, String [] nodePath, int pathPart) {
    
  }
  
  @Override
  public void onSubArticleClick(String subArticleName) {
    // TODO Auto-generated method stub
    
  }
  public List<I_AdView> getAdViews() {
    return adViews_;
  }
  
  public void setAdViews(List<I_AdView> adViews) {  
    adViews_.clear();
    if (adViews != null) {
      adViews_.addAll(adViews);
    }
  }
  
  private void reloadAds() {
    for (I_AdView adView: adViews_) {
      adView.reloadAd();
    }
  }
  public I_DialogView getDialogView() {
    return dialogView_;
  }
  public void setDialogView(I_DialogView dialogView) {
    this.dialogView_ = dialogView;
  }
  
  @Override
  public void onFailure(Throwable caught) {
    dialogView_.resetDefaults();
    if (requestedTreeId_ != null) {
      String error = CONSTANTS.getObtainArticleTreeError();
      String webMaster = CONSTANTS.getWebMaster();
      String errorSize = CONSTANTS.getObtainArticleTreeErrorSize();
      SizeParser sp = new SizeParser(errorSize);
      dialogView_.setMessageText(error + webMaster, true);
      dialogView_.setHeight(sp.getHeight());
      dialogView_.setWidth(sp.getWidth());
    } else if (requestedArticleBriefs_.size() >= 1) {
      String error = CONSTANTS.getObtainArticleBriefError();
      String webMaster = CONSTANTS.getWebMaster();
      String errorSize = CONSTANTS.getObtainArticleBriefErrorSize();
      SizeParser sp = new SizeParser(errorSize);
      dialogView_.setMessageText(error + webMaster, true);
      dialogView_.setHeight(sp.getHeight());
      dialogView_.setWidth(sp.getWidth());
    } else {
      
    }
    dialogView_.show();
  }
  
  @Override
  public void onSuccess(I_Article article) {
    // TODO Auto-generated method stub
    
  }
  
  @SuppressWarnings("boxing")
  @Override
  public void onSuccess(List<I_ArticleBrief> briefs) {
    int treeId = currentTree_.getId();
    for (I_ArticleBrief articleBrief: briefs) {
      requestedArticleBriefs_.remove(articleBrief.getId());
      cache_.addArticleBreif(treeId, articleBrief);
    }
    
    if (requestedArticleBriefs_.size() == 0) {
      displayTree();
    }
  }
  
  @SuppressWarnings("boxing")
  @Override
  public void onSuccess(I_ArticleTree tree) {
    requestedTreeId_ = null;
    currentTree_ = tree;
    List<Integer> top = currentTree_.getTop();
    
    loadArticleBriefs(top);
    for (Integer id: top) {
      loadChildNodes(new int[] {id});
    }
  } 

  @SuppressWarnings("boxing")
  private void loadArticleBriefs(List<Integer> ids) {
    Set<Integer> topCopy = new HashSet<Integer>();
    for (Integer id: ids) {
      int groupStartId = findStartArticleBriefGroup(id);
      if (!requestedArticleBriefs_.contains(groupStartId)) {
        topCopy.add(groupStartId);
      }
    }
    
    cache_.removeCachedArticleIds(topCopy);
    if (topCopy.size() >= 1) {
      requestedArticleBriefs_.addAll(topCopy);
      contentManager_.requestArticleBriefs(topCopy, this);
    }
  }
  
  @SuppressWarnings("boxing")
  private void loadChildNodes(int [] parentIds) {
    List<Integer> ids = currentTree_.getIds(parentIds);
    if (ids == null) {
      return;
    }
    loadArticleBriefs(ids);
    for (Integer id: ids) {
      int [] nextParentIds = new int [parentIds.length + 1];
      for (int i = 0; i < parentIds.length; i++) {
        nextParentIds[i] = parentIds[i];
      }
      nextParentIds[parentIds.length] = id;
      loadChildNodes(nextParentIds);
    }
  }
  
  @SuppressWarnings("boxing")
  private void displayTree() {
    articleTreeView_.clearNodes();
    List<Integer> topIds = currentTree_.getTop();
    for (Integer id: topIds) {
      I_ArticleBrief articleBrief = cache_.getArticleBrief(id);
      String articleName = articleBrief.getName();
      articleTreeView_.addNode(new String[] {articleName});
      addChildNodes(new int[]{id}, Collections.singletonList(articleName));
    }
  }
  
  @SuppressWarnings("boxing")
  public void addChildNodes(int [] parentIds, List<String> parents) {
    List<Integer> ids = currentTree_.getIds(parentIds);
    if (ids == null) {
      return;
    }
    for (Integer id: ids) {
      I_ArticleBrief article = cache_.getArticleBrief(id);
      String name = article.getName();
      String [] path = new String[parents.size() + 1];
      for (int i = 0; i < path.length - 1; i++) {
        path[i] = parents.get(i);
      }
      path[parents.size()] = name;
      
      articleTreeView_.addNode(path);
      List<String> nodeParents = new ArrayList<String>(parents);
      nodeParents.add(name);
      int [] nextParentIds = new int [parentIds.length + 1];
      for (int i = 0; i < parentIds.length; i++) {
        nextParentIds[i] = parentIds[i];
      }
      nextParentIds[parentIds.length] = id;
      addChildNodes(nextParentIds, nodeParents);
    }
  }
  
  @SuppressWarnings("boxing")
  private int findStartArticleBriefGroup(int id) {
    if (id < 100) {
      return 0;
    } else {
      String idString = new String(""+ id);
      idString = idString.substring(0, idString.length() - 2) + "00";
      return new Integer(idString);
    }
  }
}
