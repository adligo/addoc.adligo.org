package org.adligo.addoc.client.view;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

import org.adligo.addoc.client.i18n.AddocI18nConstants;

public class ArticleVersionView extends Composite {
  private AddocI18nConstants CONSTANTS = GWT.create(AddocI18nConstants.class);
  
  private Label versionLabel_;
  
  public ArticleVersionView() {
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    initWidget(horizontalPanel);
    
    Label previousVersionLabel = new Label(CONSTANTS.getLastModified());
    previousVersionLabel.setWordWrap(false);
    horizontalPanel.add(previousVersionLabel);
    horizontalPanel.setCellHorizontalAlignment(previousVersionLabel, HasHorizontalAlignment.ALIGN_RIGHT);
    
    SimplePanel simplePanel = new SimplePanel();
    simplePanel.setStyleName("articleSpacer");
    horizontalPanel.add(simplePanel);
    
    versionLabel_ = new Label("12/31/2999");
    versionLabel_.setWordWrap(false);
    horizontalPanel.add(versionLabel_);
  }

  public void setText(String text) {
    versionLabel_.setText(text);
  }
}
