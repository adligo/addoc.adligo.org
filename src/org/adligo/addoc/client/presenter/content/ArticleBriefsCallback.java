package org.adligo.addoc.client.presenter.content;

import com.google.gwt.core.client.RunAsyncCallback;

import org.adligo.addoc.client.i18n.GWTCreateWrapper;
import org.adligo.addoc.client.i18n.OneHundredArticleBriefs;
import org.adligo.addoc.client.models.ArticleBriefBuilder;
import org.adligo.addoc.client.models.I_ArticleBrief;
import org.adligo.addoc.client.models.IdRange;

import java.util.List;

public class ArticleBriefsCallback implements RunAsyncCallback {
  private GWTCreateWrapper<OneHundredArticleBriefs> briefsCreator_;
  private IdRange range_;
  private I_ArticleBriefRequestor requestor_;
  
  public void onFailure(Throwable caught) {
    requestor_.onFailure(caught);
  }

  public void onSuccess() {
    int start = range_.getStart();
    int end = range_.getEnd();
    
    OneHundredArticleBriefs briefs = briefsCreator_.create();
    List<I_ArticleBrief> articleBriefs =  ArticleBriefBuilder.buildBriefs(briefs, start, end);
    requestor_.onSuccess(articleBriefs);
  }
  
  public GWTCreateWrapper<OneHundredArticleBriefs> getBriefsCreator() {
    return briefsCreator_;
  }

  public void setBriefsCreator(GWTCreateWrapper<OneHundredArticleBriefs> briefsCreator) {
    this.briefsCreator_ = briefsCreator;
  }

  public IdRange getRange() {
    return range_;
  }

  public I_ArticleBriefRequestor getRequestor() {
    return requestor_;
  }

  public void setRange(IdRange range) {
    this.range_ = range;
  }

  public void setRequestor(I_ArticleBriefRequestor requestor) {
    this.requestor_ = requestor;
  }
}
