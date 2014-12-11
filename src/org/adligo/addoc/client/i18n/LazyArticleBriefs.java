package org.adligo.addoc.client.i18n;

import org.adligo.addoc.client.models.IdRange;

public class LazyArticleBriefs {
  private Class<? extends OneHundredArticleBriefs> oneHundredArticleBriefs_;
  private IdRange idRange_;
  
  public LazyArticleBriefs(Class<? extends OneHundredArticleBriefs> oneHundredArticleBriefs, IdRange idRange) {
    oneHundredArticleBriefs_ = oneHundredArticleBriefs;
    idRange_ = idRange;
    if (idRange_.getRange() != 100) {
      throw new IllegalArgumentException("LazyArticleBriefsTree must contain 100 article breifs.");
    }
  }
  
  public Class<? extends OneHundredArticleBriefs> getOneHundredArticleBriefs() {
    return oneHundredArticleBriefs_;
  }
  public IdRange getIds() {
    return idRange_;
  }

  
}
