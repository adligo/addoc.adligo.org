package org.adligo.addoc.client.presenter.content;

import org.adligo.addoc.client.models.I_AddocContent;

import java.util.Collection;

public interface I_ContentManager {
  public String getLastModifiedDate();
  public int getLatestTree();
  public I_AddocContent getAddocContent();
  
  public void requestTree(int treeId, I_ArticleTreeRequestor requestor);
  public void requestArticleBriefs(Collection<Integer> articleIds, I_ArticleBriefRequestor requestor);
  public void requestArticle(int articleId, I_ArticleRequestor requestor);
  
}
