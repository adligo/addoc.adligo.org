package org.adligo.addoc.client.presenter;

import org.adligo.addoc.client.models.I_AddocContent;
import org.adligo.addoc.client.models.I_ArticleContent;
import org.adligo.addoc.client.models.I_SimplePanelContent;
import org.adligo.addoc.client.ui.Dimension;
import org.adligo.addoc.client.ui.I_AdColumnView;
import org.adligo.addoc.client.ui.I_ArticleView;
import org.adligo.addoc.client.ui.I_MainView;
import org.adligo.addoc.client.ui.I_MenuView;
import org.adligo.addoc.client.ui.I_ViewFactory;

public class MainPresenter  {
  private I_MainView mainView_;
  private I_AdColumnView adColumnView_;
  private I_MenuView menuView_;
  private I_ArticleView articleView_;
  
  
  public MainPresenter(I_ViewFactory factory, I_AddocContent config) {
    mainView_ = factory.createMainView();
    adColumnView_ = factory.createAdColumnView();
   
    articleView_ = factory.createArticleView();
   
    setupMenu(factory, config);
    setupAdColumn(config);
    mainView_.setAdColumnView(adColumnView_);
    mainView_.setArticleView(articleView_);
    mainView_.show();
    
    ArticlePresenter ap = new ArticlePresenter();
    ap.setContent(config.getArticleContent());
    ap.setMenuView(menuView_);
    ap.setArticleView(articleView_);
    ap.setArticleTreeView(factory.createArticleTreeView());
    ap.start();
  }

  public void setupMenu(I_ViewFactory factory, I_AddocContent config) {
    menuView_ = factory.createMenuView();
    
    I_SimplePanelContent titleImage = config.getTitleImage();
    if (titleImage == null) {
      throw new NullPointerException("I_AddocContent requires a header image");
    }
    I_SimplePanelContent headerAd = config.getHeaderAd();
    int height = titleImage.getHeight();
    int width = titleImage.getWidth();
    
    if (headerAd != null) {
      if (headerAd.getHeight() > height) {
        height = headerAd.getHeight();
      }
      width = width + headerAd.getWidth();
      menuView_.setAd(headerAd);
    }
    menuView_.setHeight(new Dimension(height));
    menuView_.setWidth(new Dimension(width));
    menuView_.setTitleImage(titleImage);
    
    I_ArticleContent content = config.getArticleContent();
    menuView_.setLastModifiedDate(content.getLastModifiedDate());
    menuView_.render();
    mainView_.setMenuView(menuView_);
  }

  public void setupAdColumn(I_AddocContent config) {
    I_SimplePanelContent [] rightAds = config.getRightAds();
    for (int i = 0; i < rightAds.length; i++) {
      adColumnView_.addAd(rightAds[i]);
    }
  }
  
  public I_MainView getMainView() {
    return mainView_;
  }

  public I_AdColumnView getAdColumnView() {
    return adColumnView_;
  }

  public I_MenuView getMenuView() {
    return menuView_;
  }

  public I_ArticleView getArticleView() {
    return articleView_;
  }

}
