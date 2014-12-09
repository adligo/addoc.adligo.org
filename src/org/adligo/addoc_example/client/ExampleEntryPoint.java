package org.adligo.addoc_example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import org.adligo.addoc.client.controller.AddocController;
import org.adligo.addoc.client.models.AddocContentMutant;
import org.adligo.addoc.client.models.ArticleContent;
import org.adligo.addoc.client.models.ArticleContentBuilder;
import org.adligo.addoc.client.models.SimplePanelContent;
import org.adligo.addoc_example.client.i18n.ExampleI18nArticleConstants;
import org.adligo.addoc_example.client.i18n.ExampleI18nTreeConstants;

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
   rightAds[0] = new SimplePanelContent(160, 600, "ads/bidvertiser.com/sky.html");
   rightAds[1] = new SimplePanelContent(160, 600, "ads/bidvertiser.com/sky.html");
   content.setRightAds(rightAds);

   ArticleContentBuilder builder = new ArticleContentBuilder();
   builder.addArticles((ExampleI18nArticleConstants) GWT.create(ExampleI18nArticleConstants.class), 0, 6);
   builder.addTrees((ExampleI18nTreeConstants) GWT.create(ExampleI18nTreeConstants.class), 0, 2);
   ArticleContent acm = builder.toContent();
   content.setArticleContent(acm);
   
   new AddocController(content);
  
  }
}
