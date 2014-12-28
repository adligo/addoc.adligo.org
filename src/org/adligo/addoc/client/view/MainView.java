package org.adligo.addoc.client.view;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.adligo.addoc.client.ui.Dimension;
import org.adligo.addoc.client.ui.I_AdColumnView;
import org.adligo.addoc.client.ui.I_ArticleView;
import org.adligo.addoc.client.ui.I_MainView;
import org.adligo.addoc.client.ui.I_MenuView;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class MainView extends Composite implements I_MainView {
  private SimplePanel menuPanel_;
  private SimplePanel articlePanel_;
  private SimplePanel rightAdColumnPanel_;
  private I_MenuView menuView_;
  private I_ArticleView articleView_;
  private I_AdColumnView rightAdColumnView_;
  private I_AdColumnView leftAdColumnView_;
  private HorizontalPanel bottomPanel_;
  private SimplePanel leftAdColumnPanel_;
  
  public MainView() {
    
    VerticalPanel verticalPanel = new VerticalPanel();
    verticalPanel.setSpacing(3);
    initWidget(verticalPanel);
    
    menuPanel_ = new SimplePanel();
    verticalPanel.add(menuPanel_);
    
    bottomPanel_ = new HorizontalPanel();
    verticalPanel.add(bottomPanel_);
    bottomPanel_.setWidth("100%");
    
    leftAdColumnPanel_ = new SimplePanel();
    bottomPanel_.add(leftAdColumnPanel_);
    
    articlePanel_ = new SimplePanel();
    bottomPanel_.add(articlePanel_);
    bottomPanel_.setCellHeight(articlePanel_, "100%");
    bottomPanel_.setCellWidth(articlePanel_, "100%");
    articlePanel_.setStyleName(".articleView");
    bottomPanel_.setCellVerticalAlignment(articlePanel_, HasVerticalAlignment.ALIGN_TOP);
    bottomPanel_.setCellHorizontalAlignment(articlePanel_, HasHorizontalAlignment.ALIGN_CENTER);
    
    rightAdColumnPanel_ = new SimplePanel();
    bottomPanel_.add(rightAdColumnPanel_);
    bottomPanel_.setCellHorizontalAlignment(rightAdColumnPanel_, HasHorizontalAlignment.ALIGN_RIGHT);
  }

  public void setMenuView(I_MenuView menuView) {
    menuView_ = menuView;
    menuPanel_.add((Composite) menuView);
    
  }

  public I_ArticleView getArticleView() {
    return articleView_;
  }

  public void setArticleView(I_ArticleView articleView) {
    articleView_ = articleView;
    articlePanel_.clear();
    articlePanel_.add((Composite) articleView);
  }

  public I_AdColumnView getRightAdColumnView() {
    return rightAdColumnView_;
  }

  public void setRightAdColumnView(I_AdColumnView adColumnView) {
    rightAdColumnView_ = adColumnView;
    rightAdColumnPanel_.clear();
    rightAdColumnPanel_.add((Composite) adColumnView);
  }

  public I_AdColumnView getLeftAdColumnView() {
    return leftAdColumnView_;
  }

  public void setLeftAdColumnView(I_AdColumnView adColumnView) {
    leftAdColumnView_ = adColumnView;
    leftAdColumnPanel_.clear();
    leftAdColumnPanel_.add((Composite) adColumnView);
  }
  
  public I_MenuView getMenuView() {
    return menuView_;
  }

  @Override
  public void show() {
    RootPanel.get().add(this);
  }

}
