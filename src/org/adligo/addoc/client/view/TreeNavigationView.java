package org.adligo.addoc.client.view;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class TreeNavigationView extends Composite {
  private Label lblNewLabel;
  private Label dateLabel;
  private Anchor currentVersionAnchor;
  private Anchor nextAnchor;
  private Anchor previousAnchor;
  public TreeNavigationView() {
    
    VerticalPanel verticalPanel = new VerticalPanel();
    initWidget(verticalPanel);
    verticalPanel.setHeight("45px");
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    verticalPanel.add(horizontalPanel);
    
    lblNewLabel = new Label("Version:   ");
    lblNewLabel.setWordWrap(false);
    horizontalPanel.add(lblNewLabel);
    
    dateLabel = new Label("12/31/2999");
    dateLabel.setWordWrap(false);
    horizontalPanel.add(dateLabel);
    
    HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
    verticalPanel.add(horizontalPanel_1);
    
    previousAnchor = new Anchor("previous");
    previousAnchor.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        onPreviousClick();
      }
    });
    previousAnchor.setWordWrap(false);
    horizontalPanel_1.add(previousAnchor);
    
    SimplePanel simplePanel_2 = new SimplePanel();
    horizontalPanel_1.add(simplePanel_2);
    simplePanel_2.setWidth("20px");
    
    nextAnchor = new Anchor("next");
    nextAnchor.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        onNextClick();
      }
    });
    nextAnchor.setWordWrap(false);
    horizontalPanel_1.add(nextAnchor);
    horizontalPanel_1.setCellHorizontalAlignment(nextAnchor, HasHorizontalAlignment.ALIGN_RIGHT);
    
    SimplePanel simplePanel_1 = new SimplePanel();
    verticalPanel.add(simplePanel_1);
    verticalPanel.setCellHorizontalAlignment(simplePanel_1, HasHorizontalAlignment.ALIGN_CENTER);
    verticalPanel.setCellVerticalAlignment(simplePanel_1, HasVerticalAlignment.ALIGN_MIDDLE);
    
    currentVersionAnchor = new Anchor("current");
    currentVersionAnchor.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        onCurrentClick();
      }
    });
    currentVersionAnchor.setWordWrap(false);
    simplePanel_1.setWidget(currentVersionAnchor);
    currentVersionAnchor.setSize("100%", "100%");
  }

  public void setup() {
    currentVersionAnchor.setVisible(false);
    nextAnchor.setVisible(false);
    previousAnchor.setVisible(false);
  }
  
  public void showCurrentAnchor() {
    
  }
  
  public void showNextAnchor() {
    
  }

  public void showPrevioustAnchor() {
    
  }

  private void onNextClick() {
    
  }
  
  private void onPreviousClick() {
    
  }
  
  private void onCurrentClick() {
    
  }
  
}
