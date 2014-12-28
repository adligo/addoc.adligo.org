package org.adligo.addoc.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.adligo.addoc.client.i18n.AddocI18nConstants;
import org.adligo.addoc.client.models.I_Article;
import org.adligo.addoc.client.models.I_ArticleBrief;
import org.adligo.addoc.client.ui.I_ArticleView;
import org.adligo.addoc.client.ui.I_Dimension;

import java.util.List;

public class ArticleView extends Composite implements I_ArticleView {
  private static final AddocI18nConstants CONSTANTS = GWT.create(AddocI18nConstants.class);
  private Anchor topicHyperlink_;
  private Anchor articleHyperlink_;
  private Anchor upAnchor_;
  private Anchor previousAnchor_;
  private Anchor nextAnchor_;
  private AbsolutePanel abs_;
  private VerticalPanel verticalPanel_;
  private SimplePanel lastModifiedPanel_;
  private ArticleVersionView lastModifiedView_ = new ArticleVersionView();
  private PreviousArticleVersionView previousArticleVersionView_ = null;
  private SimplePanel articleContentPanel_;
  private Label titleLabel_;
  private SimplePanel subArticlesParentPanel_;
  private int [] articleTreePath_;
  private List<I_ArticleBrief> subArticles_;
  private HorizontalPanel versionPanel_;
  
  
  public ArticleView() {
    abs_ = new AbsolutePanel();
    abs_.setStyleName("articleView");
    initWidget(abs_);
    
    Image backroundPngImage = new Image("images/Backround.png");
    backroundPngImage.setStyleName("articleView");
    backroundPngImage.setSize("100%", "100%");
    abs_.add(backroundPngImage,0,0);
    
    verticalPanel_ = new VerticalPanel();
    abs_.add(verticalPanel_,0,0);
    verticalPanel_.setWidth("100%");
    Element element = verticalPanel_.getElement();
    element.setAttribute("z-index", "1000");
    
    HorizontalPanel topHorizontalPanel = new HorizontalPanel();
    verticalPanel_.add(topHorizontalPanel);
    topHorizontalPanel.setSize("100%", "");
    
    VerticalPanel verticalPanel_1 = new VerticalPanel();
    topHorizontalPanel.add(verticalPanel_1);
    verticalPanel_1.setWidth("100%");
    
    titleLabel_ = new Label("Article Title");
    titleLabel_.setStyleName("articleTitle");
    verticalPanel_1.add(titleLabel_);
    verticalPanel_1.setCellHorizontalAlignment(titleLabel_, HasHorizontalAlignment.ALIGN_CENTER);
    titleLabel_.setWidth("");
    
    versionPanel_ = new HorizontalPanel();
    versionPanel_.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    verticalPanel_1.add(versionPanel_);
    verticalPanel_1.setCellWidth(versionPanel_, "100%");
    verticalPanel_1.setCellHorizontalAlignment(versionPanel_, HasHorizontalAlignment.ALIGN_CENTER);
    versionPanel_.setWidth("100%");
    
    lastModifiedPanel_ = new SimplePanel();
    versionPanel_.add(lastModifiedPanel_);
    versionPanel_.setCellHorizontalAlignment(lastModifiedPanel_, HasHorizontalAlignment.ALIGN_CENTER);
    
    SimplePanel simplePanel_3 = new SimplePanel();
    simplePanel_3.setStyleName("articleSectionSpacer");
    verticalPanel_.add(simplePanel_3);
    
    HorizontalPanel middleHorizontalPanel = new HorizontalPanel();
    verticalPanel_.add(middleHorizontalPanel);
    middleHorizontalPanel.setWidth("100%");
    verticalPanel_.setCellWidth(middleHorizontalPanel, "100%");
    
    SimplePanel articleLeftIndentPanel = new SimplePanel();
    middleHorizontalPanel.add(articleLeftIndentPanel);
    articleLeftIndentPanel.setWidth("4px");
    
    VerticalPanel articlePanel = new VerticalPanel();
    middleHorizontalPanel.add(articlePanel);
    articlePanel.setWidth("100%");
    middleHorizontalPanel.setCellWidth(articlePanel, "100%");
    
    articleContentPanel_ = new SimplePanel();
    articlePanel.add(articleContentPanel_);
    articlePanel.setWidth("100%");
    articlePanel.setCellWidth(articleContentPanel_, "100%");
    SimplePanel spacer = new SimplePanel();
    spacer.setStyleName("articleSectionSpacer");
    articlePanel.add(spacer);
    
    VerticalPanel backLinksPanel = new VerticalPanel();
    articlePanel.add(backLinksPanel);
    articlePanel.setCellWidth(backLinksPanel, "100%");
    backLinksPanel.setWidth("100%");
    
    SimplePanel spacer2 = new SimplePanel();
    spacer2.setStyleName("articleSectionSpacer");
    articlePanel.add(spacer2);
    
    Label backlinksLabel = new Label(CONSTANTS.getBacklinks());
    backlinksLabel.setStyleName("articleSection");
    backLinksPanel.add(backlinksLabel);
    backLinksPanel.setCellHorizontalAlignment(backlinksLabel, HasHorizontalAlignment.ALIGN_CENTER);
    backLinksPanel.setCellVerticalAlignment(backlinksLabel, HasVerticalAlignment.ALIGN_MIDDLE);
    
    VerticalPanel backlinkInnerPanel = new VerticalPanel();
    backLinksPanel.add(backlinkInnerPanel);
    backLinksPanel.setCellWidth(backlinkInnerPanel, "100%");
    backLinksPanel.setCellHorizontalAlignment(backlinkInnerPanel, HasHorizontalAlignment.ALIGN_CENTER);
    
    Label lblNewLabel_1 = new Label(CONSTANTS.getTopic());
    lblNewLabel_1.setWordWrap(false);
    lblNewLabel_1.setStyleName("articleSection");
    backlinkInnerPanel.add(lblNewLabel_1);
    
    topicHyperlink_ = new Anchor("www.adligo.org/org_docs/index#topic/article");
    topicHyperlink_.setWordWrap(false);
    backlinkInnerPanel.add(topicHyperlink_);
    
    Label lblNewLabel_3 = new Label(CONSTANTS.getArticle());
    lblNewLabel_3.setStyleName("articleSection");
    lblNewLabel_3.setWordWrap(false);
    backlinkInnerPanel.add(lblNewLabel_3);
    
    articleHyperlink_ = new Anchor("www.adligo.org/org_docs/index#article/0");
    backlinkInnerPanel.add(articleHyperlink_);
    
    subArticlesParentPanel_ = new SimplePanel();
    articlePanel.add(subArticlesParentPanel_);
    subArticlesParentPanel_.setHeight("0px");
    
    SimplePanel articleRightIndentPanel = new SimplePanel();
    middleHorizontalPanel.add(articleRightIndentPanel);
    middleHorizontalPanel.setCellHorizontalAlignment(articleRightIndentPanel, HasHorizontalAlignment.ALIGN_RIGHT);
    articleRightIndentPanel.setWidth("4px");
    
    SimplePanel simplePanel_4 = new SimplePanel();
    simplePanel_4.setStyleName("articleSectionSpacer");
    verticalPanel_.add(simplePanel_4);
    
    HorizontalPanel bottomHorizontalPanel = new HorizontalPanel();
    verticalPanel_.add(bottomHorizontalPanel);
    bottomHorizontalPanel.setWidth("100%");
    verticalPanel_.setCellVerticalAlignment(bottomHorizontalPanel, HasVerticalAlignment.ALIGN_BOTTOM);
    
    VerticalPanel verticalPanel_2 = new VerticalPanel();
    bottomHorizontalPanel.add(verticalPanel_2);
    verticalPanel_2.setWidth("100%");
    bottomHorizontalPanel.setCellWidth(verticalPanel_2, "100%");
    
    Label navigationLabel = new Label(CONSTANTS.getNavigation());
    navigationLabel.setStyleName("articleSection");
    verticalPanel_2.add(navigationLabel);
    verticalPanel_2.setCellHorizontalAlignment(navigationLabel, HasHorizontalAlignment.ALIGN_CENTER);
    
    upAnchor_ = new Anchor(CONSTANTS.getUp());
    upAnchor_.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
      }
    });
    upAnchor_.setWordWrap(false);
    upAnchor_.setStyleName("articleSection");
    verticalPanel_2.add(upAnchor_);
    verticalPanel_2.setCellHorizontalAlignment(upAnchor_, HasHorizontalAlignment.ALIGN_CENTER);
    
    SimplePanel simplePanel_2 = new SimplePanel();
    verticalPanel_2.add(simplePanel_2);
    verticalPanel_2.setCellHorizontalAlignment(simplePanel_2, HasHorizontalAlignment.ALIGN_CENTER);
    
    HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
    horizontalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    simplePanel_2.setWidget(horizontalPanel_1);
    horizontalPanel_1.setSize("", "");
    
    previousAnchor_ = new Anchor(CONSTANTS.getPrevious());
    previousAnchor_.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
      }
    });
    previousAnchor_.setStyleName("articleSection");
    horizontalPanel_1.add(previousAnchor_);
    horizontalPanel_1.setCellHorizontalAlignment(previousAnchor_, HasHorizontalAlignment.ALIGN_RIGHT);
    
    SimplePanel simplePanel_1 = new SimplePanel();
    horizontalPanel_1.add(simplePanel_1);
    horizontalPanel_1.setCellHorizontalAlignment(simplePanel_1, HasHorizontalAlignment.ALIGN_CENTER);
    horizontalPanel_1.setCellVerticalAlignment(simplePanel_1, HasVerticalAlignment.ALIGN_MIDDLE);
    simplePanel_1.setWidth("10px");
    
    nextAnchor_ = new Anchor(CONSTANTS.getNext());
    nextAnchor_.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
      }
    });
    nextAnchor_.setStyleName("articleSection");
    horizontalPanel_1.add(nextAnchor_);
    setStyleName("article");
  }

  @Override
  public void setTextHtml(String tHtml) {
    articleContentPanel_.clear();
    HTMLPanel panel = new HTMLPanel(tHtml);
    panel.setWidth("100%");
    articleContentPanel_.add(panel);
  }

  @Override
  public void setTopicUrl(String url) {
    topicHyperlink_.setText(url);
    topicHyperlink_.setHref(url);
  }

  @Override
  public void setArticleUrl(String url) {
    articleHyperlink_.setText(url);
    articleHyperlink_.setHref(url);
  }

  @Override
  public void setUpEnabled(boolean up) {
     upAnchor_.setVisible(up);
  }

  @Override
  public void setPreviousEnabled(boolean p) {
    previousAnchor_.setVisible(p);
    if (p) {
      if (previousArticleVersionView_ == null) {
        previousArticleVersionView_ = new PreviousArticleVersionView();
      }
      //previousVersionPanel_.add(previousArticleVersionView_);
    } else {
      if (previousArticleVersionView_ != null) {
        //previousVersionPanel_.clear();
      }
    }
  }

  @Override
  public void setNextEnabled(boolean n) {
    nextAnchor_.setVisible(n);
  }


  @Override
  public void setChildArticles(int [] articleTreePath, List<I_ArticleBrief> articles) {
    int height = articles.size() * 30;
    subArticlesParentPanel_.setHeight("0px");
    
  }
  

  @Override
  public void setTitle(String title) {
    titleLabel_.setText(title);
  }

  @Override
  public void setVersions(String previousDate, String lastModified) {
	  lastModifiedView_.setText(lastModified);
	  versionPanel_.clear();
	  if (previousDate != null) {
		  if (previousArticleVersionView_ == null) {
			  previousArticleVersionView_ = new PreviousArticleVersionView();
		  }
		  previousArticleVersionView_.setText(previousDate);
		  versionPanel_.add(previousArticleVersionView_);
		  versionPanel_.setCellHorizontalAlignment(previousArticleVersionView_, HasHorizontalAlignment.ALIGN_RIGHT);
		  
		  SimplePanel spacer = new SimplePanel();
		  spacer.setStyleName("articleSectionSpacer");
		  versionPanel_.add(spacer);
		  
		  versionPanel_.add(lastModifiedView_);
		  versionPanel_.setCellHorizontalAlignment(lastModifiedView_, HasHorizontalAlignment.ALIGN_LEFT);
		  
	  } else {
		  versionPanel_.add(lastModifiedView_);
		  versionPanel_.setCellHorizontalAlignment(lastModifiedView_, HasHorizontalAlignment.ALIGN_CENTER);
		  
	  }
	  
  }

}
