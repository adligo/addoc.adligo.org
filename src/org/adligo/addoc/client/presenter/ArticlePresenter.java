package org.adligo.addoc.client.presenter;

import com.google.gwt.user.client.Window;

import org.adligo.addoc.client.models.I_Article;
import org.adligo.addoc.client.models.I_ArticleContent;
import org.adligo.addoc.client.models.I_ArticleTree;
import org.adligo.addoc.client.ui.I_ArticleTreeView;
import org.adligo.addoc.client.ui.I_ArticleView;
import org.adligo.addoc.client.ui.I_MenuView;
import org.adligo.addoc.client.ui.handlers.AddocEvent;
import org.adligo.addoc.client.ui.handlers.I_AddocHandler;

import java.util.ArrayList;
import java.util.List;

public class ArticlePresenter implements I_AddocHandler {
  private I_MenuView menuView_;
  private I_ArticleTreeView articleTreeView_;
  private I_ArticleView articleView_;
  private I_ArticleContent content_;
  private I_ArticleTree currentTree_;
  private I_Article currentArticle_;
  
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
  }
  public I_ArticleView getArticleView() {
    return articleView_;
  }
  public void setArticleView(I_ArticleView articleView) {
    articleView_ = articleView;
  }
  public I_ArticleContent getContent() {
    return content_;
  }
  public void setContent(I_ArticleContent content) {
    content_ = content;
  }
  
  public void start() {
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
  }

  @SuppressWarnings("boxing")
  public void addChildNodes(int [] parentIds, List<String> parents) {
    List<Integer> ids = currentTree_.getIds(parentIds);
    if (ids == null) {
      return;
    }
    for (Integer id: ids) {
      I_Article article = content_.getArticle(id);
      String name = article.getName();
      String [] path = new String[parents.size() + 1];
      for (int i = 0; i < path.length - 1; i++) {
        path[i] = parents.get(i);
      }
      path[parents.size()] = name;
      
      articleTreeView_.addNode(path);
      List<String> nodeParents = new ArrayList<String>(parents);
      parents.add(name);
      int [] nextParentIds = new int [parentIds.length + 1];
      for (int i = 0; i < parentIds.length; i++) {
        nextParentIds[i] = parentIds[i];
      }
      nextParentIds[parentIds.length] = id;
      addChildNodes(nextParentIds, nodeParents);
    }
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
    // TODO Auto-generated method stub
    
  }
}
