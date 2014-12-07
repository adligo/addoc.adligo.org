package org.adligo.addoc.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.adligo.addoc.client.ui.I_AdColumnView;
import org.adligo.addoc.client.ui.I_ArticleView;
import org.adligo.addoc.client.ui.I_Dimension;
import org.adligo.addoc.client.ui.I_MainView;
import org.adligo.addoc.client.ui.I_MenuView;

public class MainView extends AbstractSizedView implements I_MainView {
  private SimplePanel menuPanel;
  private SimplePanel articlePanel;
  private SimplePanel simplePanel;
  private I_MenuView menuView;
  private I_ArticleView articleView;
  private I_AdColumnView adColumnView;
  
  public MainView() {
    
    VerticalPanel verticalPanel = new VerticalPanel();
    initWidget(verticalPanel);
    
    menuPanel = new SimplePanel();
    verticalPanel.add(menuPanel);
    
    HorizontalPanel adPanel = new HorizontalPanel();
    verticalPanel.add(adPanel);
    verticalPanel.setCellHeight(adPanel, "100%");
    adPanel.setHeight("100%");
    
    articlePanel = new SimplePanel();
    adPanel.add(articlePanel);
    articlePanel.setHeight("100%");
    adPanel.setCellHeight(articlePanel, "100%");
    
    simplePanel = new SimplePanel();
    adPanel.add(simplePanel);
  }

  public void setMenuView(I_MenuView menuView) {
    menuPanel.setSize(menuView.getWidth(), menuView.getHeight());
    menuPanel.add((Composite) menuView);
  }

  public I_ArticleView getArticleView() {
    return articleView;
  }

  public void setArticleView(I_ArticleView articleView) {
    this.articleView = articleView;
  }

  public I_AdColumnView getAdColumnView() {
    return adColumnView;
  }

  public void setAdColumnView(I_AdColumnView adColumnView) {
    this.adColumnView = adColumnView;
  }

  public I_MenuView getMenuView() {
    return menuView;
  }

  @Override
  public void show() {
    RootPanel.get().add(this);
  }
}
