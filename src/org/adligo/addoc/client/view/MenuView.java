package org.adligo.addoc.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.adligo.addoc.client.i18n.AddocI18nConstants;
import org.adligo.addoc.client.models.I_SimplePanelContent;
import org.adligo.addoc.client.ui.I_AdView;
import org.adligo.addoc.client.ui.I_MenuView;
import org.adligo.addoc.client.ui.events.AddocEvent;
import org.adligo.addoc.client.ui.events.I_AddocHandler;

public class MenuView extends AbstractSizedView implements I_MenuView {
  private static final AddocI18nConstants CONSTANTS = GWT.create(AddocI18nConstants.class);
  private VerticalPanel verticalPanel;
  private HorizontalPanel topHorizontalPanel;
  private HorizontalPanel bottomHorizontalPanel;
  private DecoratorPanel mainPanel;
  private Label dateLabel;
  private Anchor indexAnchor;
  private Image titleImage;
  private I_SimplePanelContent titleImageContent;
  private I_SimplePanelContent adContent;
  private SimplePanel adPanel;
  private SimplePanel spacer;
  private SimplePanel endSpacer;
  private I_AddocHandler handler;
  
  public MenuView() {

    mainPanel = new DecoratorPanel();
    initWidget(mainPanel);
    
    verticalPanel = new VerticalPanel();
    mainPanel.add(verticalPanel);
    
    topHorizontalPanel = new HorizontalPanel();
    verticalPanel.add(topHorizontalPanel);
    
    SimplePanel titlePanel = new SimplePanel();
    topHorizontalPanel.add(titlePanel);
    
    titleImage = new Image();
    titleImage.setStyleName("titleImageBackround");
    titlePanel.setWidget(titleImage);
    titleImage.setSize("100%", "100%");
    
    adPanel = new SimplePanel();
    topHorizontalPanel.add(adPanel);
    
    bottomHorizontalPanel = new HorizontalPanel();
    bottomHorizontalPanel.setStyleName("menuBar");
    verticalPanel.add(bottomHorizontalPanel);
    bottomHorizontalPanel.setWidth("100%");
    
    indexAnchor = new Anchor(CONSTANTS.getIndex());
    indexAnchor.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        onIndexClick();
      }
    });
    indexAnchor.setWordWrap(false);
    indexAnchor.setStyleName("menuBarIndex");
    bottomHorizontalPanel.add(indexAnchor);
    bottomHorizontalPanel.setCellVerticalAlignment(indexAnchor, HasVerticalAlignment.ALIGN_BOTTOM);
    
    spacer = new SimplePanel();
    bottomHorizontalPanel.add(spacer);
    
    Label changedLabel = new Label(CONSTANTS.getLastModified());
    changedLabel.setStyleName("menuViewText");
    changedLabel.setWordWrap(false);
    bottomHorizontalPanel.add(changedLabel);
    bottomHorizontalPanel.setCellVerticalAlignment(changedLabel, HasVerticalAlignment.ALIGN_BOTTOM);
    bottomHorizontalPanel.setCellHorizontalAlignment(changedLabel, HasHorizontalAlignment.ALIGN_RIGHT);
    
    dateLabel = new Label("12/31/2999");
    dateLabel.setWordWrap(false);
    bottomHorizontalPanel.add(dateLabel);
    bottomHorizontalPanel.setCellVerticalAlignment(dateLabel, HasVerticalAlignment.ALIGN_BOTTOM);
    bottomHorizontalPanel.setCellHorizontalAlignment(dateLabel, HasHorizontalAlignment.ALIGN_RIGHT);
    
    endSpacer = new SimplePanel();
    bottomHorizontalPanel.add(endSpacer);
    endSpacer.setWidth("6px");
    

  }

  public void setTitleImage(I_SimplePanelContent content) {
    titleImageContent = content;
    titleImage.setWidth("" + content.getWidth() + "px");
    titleImage.setHeight("" + content.getHeight()  + "px");
    titleImage.setUrl(content.getUrl());
  }
  
  public I_AdView setAd(I_SimplePanelContent content) {
    adContent = content;
    AdView adView = new AdView(content.getUrl());
   
    adView.setWidth("" + content.getWidth() + "px");
    adView.setHeight("" + content.getHeight()  + "px");
    adPanel.setWidget(adView);
    return adView;
  }

  public void setLastModifiedDate(String date) {
    dateLabel.setText(date);
  }
  
  @Override
  public void render() {
    int width = titleImageContent.getWidth();
    if (adContent != null) {
      width = width + adContent.getWidth();
    }
    width = width - 300;
    spacer.setWidth("" + width + "px");
    
  }
  
  private void onIndexClick() {
    handler.onSimpleEvent(AddocEvent.IndexClick);
  }

  public I_AddocHandler getHandler() {
    return handler;
  }

  public void setHandler(I_AddocHandler handler) {
    this.handler = handler;
  }
}
