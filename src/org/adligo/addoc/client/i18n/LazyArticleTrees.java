package org.adligo.addoc.client.i18n;

import org.adligo.addoc.client.models.IdRange;

public class LazyArticleTrees {
  private GWTCreateWrapper<TenArticleTrees> tenArticleTrees_;
  private IdRange idRange_;
  
  public LazyArticleTrees(GWTCreateWrapper<TenArticleTrees> tenArticleTrees, IdRange idRange) {
    tenArticleTrees_ = tenArticleTrees;
    idRange_ = idRange;
    if (idRange_.getRange() != 10) {
      throw new IllegalArgumentException("LazyArticleTree must contain 10 trees.");
    }
  }
   
  public GWTCreateWrapper<TenArticleTrees> getTenArticleTrees() {
    return tenArticleTrees_;
  }
  
  public IdRange getIds() {
    return idRange_;
  }

  
}
