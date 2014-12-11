package org.adligo.addoc.client.view;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

import org.adligo.addoc.client.i18n.AddocI18nConstants;

public class PreviousArticleVersionView extends Composite {
  private AddocI18nConstants CONSTANTS = GWT.create(AddocI18nConstants.class);
  
  private Anchor previousVersionAnchor_;
  
  public PreviousArticleVersionView() {
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    initWidget(horizontalPanel);
    
    Label previousVersionLabel = new Label(CONSTANTS.getPreviousVersion());
    previousVersionLabel.setWordWrap(false);
    horizontalPanel.add(previousVersionLabel);
    
    SimplePanel simplePanel = new SimplePanel();
    horizontalPanel.add(simplePanel);
    
    previousVersionAnchor_ = new Anchor("12/31/2999");
    previousVersionAnchor_.setWordWrap(false);
    horizontalPanel.add(previousVersionAnchor_);
  }

  public void addVersionClickHandler(ClickHandler handler) {
    previousVersionAnchor_.addClickHandler(handler);
  }

  public void setText(String text) {
    previousVersionAnchor_.setText(text);
  }
}
