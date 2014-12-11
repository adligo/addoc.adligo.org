package org.adligo.addoc_example.client;

import com.google.gwt.core.client.EntryPoint;

import org.adligo.addoc.client.controller.AddocController;
import org.adligo.addoc.client.i18n.LazyArticleBriefs;
import org.adligo.addoc.client.i18n.LazyArticleTrees;
import org.adligo.addoc.client.models.AddocContentMutant;
import org.adligo.addoc.client.models.ContentCache;
import org.adligo.addoc.client.models.IdRange;
import org.adligo.addoc.client.models.SimplePanelContent;
import org.adligo.addoc.client.models.SystemTimepeice;
import org.adligo.addoc.client.presenter.content.ContentManager;
import org.adligo.addoc_example.client.i18n.ExampleI18nArticleConstants;
import org.adligo.addoc_example.client.i18n.ExampleI18nTreeConstants;

import java.util.Collections;

/**
 * This is a example usage of the addoc.adligo.org code
 * to display a simple documentation with history,
 * url link history support, navigation and
 * adds (they make money!).
 * 
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ExampleEntryPoint implements EntryPoint {
  
  @SuppressWarnings("unused")
  public void onModuleLoad() {
   AddocContentMutant content = new AddocContentMutant();
   SimplePanelContent titleImage = new SimplePanelContent(90, 500, "images/beeTitle.png");
   content.setTitleImage(titleImage);
   SimplePanelContent headerAd = new SimplePanelContent(90, 728, "ads/bidvertiser.com/leaderboard.html");
   content.setHeaderAd(headerAd);
   
   SimplePanelContent [] rightAds = new SimplePanelContent[2];
   rightAds[0] = new SimplePanelContent(600, 160, "ads/bidvertiser.com/sky.html");
   rightAds[1] = new SimplePanelContent(600, 160, "ads/bidvertiser.com/sky.html");
   content.setRightAds(rightAds);
   content.setLeftAds(rightAds);
   content.setContentCache(new ContentCache(new SystemTimepeice()));
   
   ContentManager cm = new ContentManager();
   LazyArticleTrees lat = new LazyArticleTrees(ExampleI18nTreeConstants.class, new IdRange(0,9));
   cm.setupArticleTrees(Collections.singletonList(lat), 2);
   
   LazyArticleBriefs lab = new LazyArticleBriefs(ExampleI18nArticleConstants.class, new IdRange(0,99));
   cm.setupArticleBriefs(Collections.singletonList(lab), 6);
   
   cm.setAddocContent(content);
   
   new AddocController(cm);
  
  }
}
