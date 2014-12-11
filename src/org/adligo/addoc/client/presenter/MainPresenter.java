package org.adligo.addoc.client.presenter;

import org.adligo.addoc.client.models.I_AddocContent;
import org.adligo.addoc.client.models.I_SimplePanelContent;
import org.adligo.addoc.client.presenter.content.I_ContentManager;
import org.adligo.addoc.client.ui.Dimension;
import org.adligo.addoc.client.ui.I_AdColumnView;
import org.adligo.addoc.client.ui.I_ArticleView;
import org.adligo.addoc.client.ui.I_MainView;
import org.adligo.addoc.client.ui.I_MenuView;
import org.adligo.addoc.client.ui.I_ViewFactory;

public class MainPresenter  {
  private I_MainView mainView_;
  private I_AdColumnView rightAdColumnView_;
  private I_AdColumnView leftAdColumnView_;
  private I_MenuView menuView_;
  private I_ArticleView articleView_;
  
  public MainPresenter(I_ViewFactory factory, I_ContentManager cm) {
    mainView_ = factory.createMainView();
   
    articleView_ = factory.createArticleView();
   
    setupMenu(factory, cm.getAddocContent(), cm.getLastModifiedDate());
    setupAdColumns(factory, cm.getAddocContent());
    
    mainView_.setArticleView(articleView_);
    mainView_.show();
    
    ArticlePresenter ap = new ArticlePresenter();
    ap.setContentManager(cm);
    ap.setMenuView(menuView_);
    ap.setArticleView(articleView_);
    ap.setDialogView(factory.createDialogView());
    ap.setArticleTreeView(factory.createArticleTreeView());
    ap.start(mainView_.getBrowserUrl());
  }

  public void setupMenu(I_ViewFactory factory, I_AddocContent config, String lastModifiedDate) {
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
    
    menuView_.setLastModifiedDate(lastModifiedDate);
    menuView_.render();
    mainView_.setMenuView(menuView_);
  }

  public void setupAdColumns(I_ViewFactory factory, I_AddocContent config) {
    I_SimplePanelContent [] rightAds = config.getRightAds();
    if (rightAds.length >= 1) {
      rightAdColumnView_ = factory.createAdColumnView();
      for (int i = 0; i < rightAds.length; i++) {
        rightAdColumnView_.addAd(rightAds[i]);
      }
      mainView_.setRightAdColumnView(rightAdColumnView_);
    }
    I_SimplePanelContent [] leftAds = config.getLeftAds();
    if (leftAds.length >= 1) {
      leftAdColumnView_ = factory.createAdColumnView();
      for (int i = 0; i < leftAds.length; i++) {
        leftAdColumnView_.addAd(leftAds[i]);
      }
      mainView_.setLeftAdColumnView(leftAdColumnView_);
    }
  }
  
  public I_MainView getMainView() {
    return mainView_;
  }

  public I_AdColumnView getAdColumnView() {
    return rightAdColumnView_;
  }

  public I_MenuView getMenuView() {
    return menuView_;
  }

  public I_ArticleView getArticleView() {
    return articleView_;
  }

}
