package org.adligo.addoc.client.presenter;

import org.adligo.addoc.client.bi.I_Browser;
import org.adligo.addoc.client.models.I_AddocContent;
import org.adligo.addoc.client.models.I_SimplePanelContent;
import org.adligo.addoc.client.presenter.content.I_ContentManager;
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
  
  public MainPresenter(I_ViewFactory factory, I_Browser browser, I_ContentManager cm) {
    mainView_ = factory.createMainView();
   
    setupArticleView(factory);
   
    setupMenu(factory, cm.getAddocContent());
    setupAdColumns(factory, cm.getAddocContent());
    
    mainView_.setArticleView(articleView_);
    mainView_.show();
    
    ArticlePresenter ap = new ArticlePresenter();
    ap.setContentManager(cm);
    ap.setMenuView(menuView_);
    ap.setArticleView(articleView_);
    ap.setDialogView(factory.createDialogView());
    ap.setArticleTreeView(factory.createArticleTreeView());
    ap.start(browser.getBrowserAddress());
  }

  public void setupArticleView(I_ViewFactory factory) {
    articleView_ = factory.createArticleView();
    articleView_.setTitle("");
    articleView_.setVersions(null, "");
    articleView_.setTextHtml("");
    articleView_.setArticleUrl("");
    articleView_.setTopicUrl("");
    articleView_.setNextEnabled(false);
    articleView_.setPreviousEnabled(false);
    articleView_.setUpEnabled(false);
  }

  public void setupMenu(I_ViewFactory factory, I_AddocContent config) {
    menuView_ = factory.createMenuView();
    
    I_SimplePanelContent titleImage = config.getTitleImage();
    if (titleImage == null) {
      throw new NullPointerException("I_AddocContent requires a header image");
    }
    I_SimplePanelContent headerAd = config.getHeaderAd();
    menuView_.setAd(headerAd);
    menuView_.setTitleImage(titleImage);
    
    menuView_.setLastModifiedDate("");
    mainView_.setMenuView(menuView_);
  }

  public void setupAdColumns(I_ViewFactory factory, I_AddocContent config) {
    I_SimplePanelContent [] rightFrames = config.getRightFrames();
    if (rightFrames.length >= 1) {
      rightAdColumnView_ = factory.createAdColumnView();
      for (int i = 0; i < rightFrames.length; i++) {
        I_SimplePanelContent pc = rightFrames[i];
        String url = pc.getUrl();
        if (url == null) {
          rightAdColumnView_.addPanel(pc);
        } else {
          rightAdColumnView_.addAd(pc);
        }
      }
      mainView_.setRightAdColumnView(rightAdColumnView_);
    }
    I_SimplePanelContent [] leftFrames = config.getLeftFrames();
    if (leftFrames.length >= 1) {
      leftAdColumnView_ = factory.createAdColumnView();
      for (int i = 0; i < leftFrames.length; i++) {
        I_SimplePanelContent pc = rightFrames[i];
        String url = pc.getUrl();
        if (url == null) {
          leftAdColumnView_.addPanel(pc);
        } else {
          leftAdColumnView_.addAd(pc);
        }
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
