package org.adligo.addoc.client.presenter.content;

import com.google.gwt.core.client.RunAsyncCallback;

import org.adligo.addoc.client.i18n.GWTCreateWrapper;
import org.adligo.addoc.client.i18n.TenArticleTrees;
import org.adligo.addoc.client.models.ArticleTreesBuilder;
import org.adligo.addoc.client.models.I_ArticleTree;
import org.adligo.addoc.client.models.IdRange;

import java.util.Map;

public class TreesCallback implements RunAsyncCallback {
  private IdRange ids_;
  private GWTCreateWrapper<TenArticleTrees> treesClass_;
  private I_ArticleTreeRequestor requestor_;
  private Integer treeId_;
  private ArticleTreesBuilder treeBuilder_;
  private Map<Integer, I_ArticleTree> articleTrees_;
  
  public TreesCallback(Map<Integer, I_ArticleTree> articleTrees) {
    articleTrees_ = articleTrees;
  }
  
  public void onFailure(Throwable caught) {
    requestor_.onFailure(caught);
  }

  public void onSuccess() {
    TenArticleTrees trees = treesClass_.create();
    int start = ids_.getStart();
    int end = ids_.getEnd();

    Map<Integer,I_ArticleTree> treeInstances = treeBuilder_.buildTreeMap(trees, start, end);
    articleTrees_.putAll(treeInstances);
    
    I_ArticleTree tree = treeInstances.get(treeId_);
    if (tree != null) {
      requestor_.onSuccess(tree);
    }
  }

  public IdRange getIds() {
    return ids_;
  }

  public void setIds(IdRange ids) {
    this.ids_ = ids;
  }

  public GWTCreateWrapper<TenArticleTrees> getTreesClass() {
    return treesClass_;
  }

  public void setTreesClass(GWTCreateWrapper<TenArticleTrees> treesClass) {
    this.treesClass_ = treesClass;
  }

  public I_ArticleTreeRequestor getRequestor() {
    return requestor_;
  }

  public void setRequestor(I_ArticleTreeRequestor requestor) {
    this.requestor_ = requestor;
  }

  public Integer getTreeId() {
    return treeId_;
  }

  @SuppressWarnings("boxing")
  public void setTreeId(int treeId) {
    this.treeId_ = treeId;
  }


  public ArticleTreesBuilder getTreeBuilder() {
    return treeBuilder_;
  }

  public void setTreeBuilder(ArticleTreesBuilder treeBuilder) {
    this.treeBuilder_ = treeBuilder;
  }

  public Map<Integer, I_ArticleTree> getArticleTrees() {
    return articleTrees_;
  }
}
