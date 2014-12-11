package org.adligo.addoc.client.view;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.adligo.addoc.client.i18n.AddocI18nConstants;

public class SubArticlesView extends AbstractSizedView {
  private static final AddocI18nConstants CONSTANTS = GWT.create(AddocI18nConstants.class);
  private VerticalPanel subArticlePanel;
  
  public SubArticlesView() {
    
    VerticalPanel verticalPanel = new VerticalPanel();
    initWidget(verticalPanel);
    
    Label lblNewLabel = new Label(CONSTANTS.getSubArticles());
    lblNewLabel.setStyleName("articleSection");
    verticalPanel.add(lblNewLabel);
    verticalPanel.setCellHorizontalAlignment(lblNewLabel, HasHorizontalAlignment.ALIGN_CENTER);
    
    subArticlePanel = new VerticalPanel();
    verticalPanel.add(subArticlePanel);
    verticalPanel.setCellHorizontalAlignment(subArticlePanel, HasHorizontalAlignment.ALIGN_CENTER);
    verticalPanel.setCellWidth(subArticlePanel, "50%");
    
    addArticle("Sub-Article One");
    addArticle("Sub-Article Two");
    addArticle("Sub-Article Three");
  }

  public void addArticle(final String articleName) {
    Anchor anchor = new Anchor(articleName);
    anchor.setDirectionEstimator(false);
    anchor.setWordWrap(false);
    anchor.setStyleName("article");
    subArticlePanel.add(anchor);
    subArticlePanel.setCellWidth(anchor, "100%");
    setStyleName("article");
    anchor.addClickHandler(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        onArticleClick(articleName);
      }
    });
    
  }

  private void onArticleClick(String articleName) {
    
  }
}
