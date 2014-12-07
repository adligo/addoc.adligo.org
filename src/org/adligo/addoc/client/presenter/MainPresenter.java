package org.adligo.addoc.client.presenter;

import com.google.gwt.user.client.Window;

import org.adligo.addoc.client.models.I_AddocContent;
import org.adligo.addoc.client.models.I_SimplePanelContent;
import org.adligo.addoc.client.ui.Dimension;
import org.adligo.addoc.client.ui.I_AdColumnView;
import org.adligo.addoc.client.ui.I_ArticleTreeView;
import org.adligo.addoc.client.ui.I_ArticleView;
import org.adligo.addoc.client.ui.I_MainView;
import org.adligo.addoc.client.ui.I_MenuView;
import org.adligo.addoc.client.ui.I_ViewFactory;
import org.adligo.addoc.client.ui.handlers.AddocEvent;
import org.adligo.addoc.client.ui.handlers.I_AddocHandler;

public class MainPresenter implements I_AddocHandler {
  private I_MainView mainView_;
  private I_AdColumnView adColumnView_;
  private I_MenuView menuView_;
  private I_ArticleView articleView_;
  private I_ArticleTreeView articleTreeView_;
  
  public MainPresenter(I_ViewFactory factory, I_AddocContent config) {
    mainView_ = factory.createMainView();
    adColumnView_ = factory.createAdColumnView();
   
    articleView_ = factory.createArticleView();
   
    setupMenu(factory, config);
    mainView_.setAdColumnView(adColumnView_);
    mainView_.setArticleView(articleView_);
    mainView_.show();
    
    articleTreeView_ = factory.createArticleTreeView();
  }

  public void setupMenu(I_ViewFactory factory, I_AddocContent config) {
    menuView_ = factory.createMenuView();
    menuView_.setHandler(this);
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
    menuView_.render();
    mainView_.setMenuView(menuView_);
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

  @Override
  public void onSimpleEvent(AddocEvent event) {
    switch (event) {
      case IndexClick:
          articleTreeView_.show();
        break;
      default:
        Window.alert("non handled event " + event);
    }
  }
}
