package org.adligo.addoc_example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

import org.adligo.addoc.client.browser.Browser;
import org.adligo.addoc.client.controller.AddocController;
import org.adligo.addoc.client.i18n.AddocI18nConstants;
import org.adligo.addoc.client.i18n.GWTCreateWrapper;
import org.adligo.addoc.client.i18n.LazyArticleBriefs;
import org.adligo.addoc.client.i18n.LazyArticleTrees;
import org.adligo.addoc.client.i18n.OneHundredArticleBriefs;
import org.adligo.addoc.client.i18n.TenArticleTrees;
import org.adligo.addoc.client.models.AddocContentMutant;
import org.adligo.addoc.client.models.ContentCache;
import org.adligo.addoc.client.models.IdRange;
import org.adligo.addoc.client.models.SystemTimepeice;
import org.adligo.addoc.client.presenter.content.ContentManager;
import org.adligo.addoc_example.client.i18n.ExampleI18nArticleConstants;
import org.adligo.addoc_example.client.i18n.ExampleI18nTreeConstants;
import org.adligo.css.client.obtain.CssObtainer;
import org.adligo.css.client.obtain.I_CssRequester;
import org.adligo.css.shared.models.I_StyleSheet;

import java.util.Collections;

/**
 * This is a example usage of the addoc.adligo.org code
 * to display a simple documentation with history,
 * url link history support, navigation and
 * adds (they make money!).
 * 
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ExampleEntryPoint implements EntryPoint, I_CssRequester {
  private static final AddocI18nConstants CONSTANTS = GWT.create(AddocI18nConstants.class);
  private Browser browser_ = new Browser();
  private ContentManager cm_ = new ContentManager(browser_, CONSTANTS.getArticleRelativeUrl());
  
  public void onModuleLoad() {
   
   CssObtainer obtainer = new CssObtainer();
   obtainer.obtainCss("Addoc4js.css", this);
 
   addArticleTrees();
   addArticleBriefs();
  }

  private void addArticleBriefs() {
    LazyArticleBriefs lab = new LazyArticleBriefs(new GWTCreateWrapper<OneHundredArticleBriefs>() {
       
       @Override
       public OneHundredArticleBriefs create() {
         return GWT.create(ExampleI18nArticleConstants.class);
       }
     }, new IdRange(0,99));
     cm_.setupArticleBriefs(Collections.singletonList(lab), 6);
  }

  private void addArticleTrees() {
    LazyArticleTrees lat = new LazyArticleTrees(new GWTCreateWrapper<TenArticleTrees>() {
        
        @Override
        public TenArticleTrees create() {
          return GWT.create(ExampleI18nTreeConstants.class);
        }
      }, new IdRange(0,9));
     cm_.setupArticleTrees(Collections.singletonList(lat), 2);
  }

  @SuppressWarnings("unused")
  @Override
  public void onSuccess(I_StyleSheet styleSheet) {
    
    AddocContentMutant content = new AddocContentMutant(new ContentCache(new SystemTimepeice()), styleSheet, browser_);

    cm_.setAddocContent(content);
    // TODO Auto-generated method stub
    new AddocController(styleSheet, browser_, cm_);
  }

  @Override
  public void onFailure(Throwable thrown) {
    thrown.printStackTrace();
    Window.alert(thrown.getMessage());
  }
}
