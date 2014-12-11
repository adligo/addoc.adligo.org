package org.adligo.addoc.client.i18n;

import org.adligo.addoc.client.models.IdRange;

public class LazyArticleBriefs {
  private GWTCreateWrapper<OneHundredArticleBriefs> oneHundredArticleBriefs_;
  private IdRange idRange_;
  
  public LazyArticleBriefs(GWTCreateWrapper<OneHundredArticleBriefs> oneHundredArticleBriefs, IdRange idRange) {
    oneHundredArticleBriefs_ = oneHundredArticleBriefs;
    idRange_ = idRange;
    if (idRange_.getRange() != 100) {
      throw new IllegalArgumentException("LazyArticleBriefsTree must contain 100 article breifs.");
    }
  }
  
  public GWTCreateWrapper<OneHundredArticleBriefs> getOneHundredArticleBriefs() {
    return oneHundredArticleBriefs_;
  }
  public IdRange getIds() {
    return idRange_;
  }

  
}
