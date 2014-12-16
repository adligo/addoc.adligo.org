package org.adligo.addoc.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
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

public class ArticleView extends AbstractSizedView implements I_ArticleView {
  private static final AddocI18nConstants CONSTANTS = GWT.create(AddocI18nConstants.class);
  
  private Label dateLabel;
  private Anchor topicHyperlink;
  private Anchor articleHyperlink;
  private Anchor upAnchor;
  private Anchor previousAnchor;
  private Anchor nextAnchor;
  private AbsolutePanel abs;
  private VerticalPanel verticalPanel;
  private SimplePanel previousVersionPanel_;
  private PreviousArticleVersionView previousArticleVersionView_ = null;
  private SimplePanel articleContentPanel_;
  private Label titleLabel;
  private SimplePanel subArticlesParentPanel_;
  private int [] articleTreePath_;
  private List<I_ArticleBrief> subArticles_;
  
  public ArticleView() {
    abs = new AbsolutePanel();
    abs.setStyleName("articleView");
    initWidget(abs);
    
    Image backroundPngImage = new Image("images/Backround.png");
    backroundPngImage.setStyleName("articleView");
    backroundPngImage.setSize("100%", "100%");
    abs.add(backroundPngImage,0,0);
    
    verticalPanel = new VerticalPanel();
    abs.add(verticalPanel,0,0);
    Element element = verticalPanel.getElement();
    element.setAttribute("z-index", "1000");
    
    HorizontalPanel topHorizontalPanel = new HorizontalPanel();
    verticalPanel.add(topHorizontalPanel);
    topHorizontalPanel.setSize("100%", "");
    
    VerticalPanel verticalPanel_1 = new VerticalPanel();
    topHorizontalPanel.add(verticalPanel_1);
    verticalPanel_1.setWidth("100%");
    
    titleLabel = new Label("Article Title");
    titleLabel.setStyleName("articleTitle");
    verticalPanel_1.add(titleLabel);
    verticalPanel_1.setCellHorizontalAlignment(titleLabel, HasHorizontalAlignment.ALIGN_CENTER);
    titleLabel.setWidth("");
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    verticalPanel_1.add(horizontalPanel);
    verticalPanel_1.setCellWidth(horizontalPanel, CONSTANTS.horizontalPanel_width());
    verticalPanel_1.setCellHorizontalAlignment(horizontalPanel, HasHorizontalAlignment.ALIGN_CENTER);
    horizontalPanel.setWidth("");
    
    Label lastModifiedLabel = new Label(CONSTANTS.getLastModified());
    lastModifiedLabel.setWordWrap(false);
    horizontalPanel.add(lastModifiedLabel);
    horizontalPanel.setCellHorizontalAlignment(lastModifiedLabel, HasHorizontalAlignment.ALIGN_RIGHT);
    
    SimplePanel simplePanel = new SimplePanel();
    horizontalPanel.add(simplePanel);
    horizontalPanel.setCellHorizontalAlignment(simplePanel, HasHorizontalAlignment.ALIGN_CENTER);
    simplePanel.setWidth("10px");
    
    dateLabel = new Label("12/31/2999");
    dateLabel.setWordWrap(false);
    horizontalPanel.add(dateLabel);
    
    previousVersionPanel_ = new SimplePanel();
    verticalPanel_1.add(previousVersionPanel_);
    verticalPanel_1.setCellHorizontalAlignment(previousVersionPanel_, HasHorizontalAlignment.ALIGN_CENTER);
    verticalPanel_1.setCellWidth(previousVersionPanel_, CONSTANTS.previousVersionPanel__width());
    
    HorizontalPanel middleHorizontalPanel = new HorizontalPanel();
    verticalPanel.add(middleHorizontalPanel);
    middleHorizontalPanel.setWidth("100%");
    verticalPanel.setCellWidth(middleHorizontalPanel, "100%");
    
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
    
    VerticalPanel backLinksPanel = new VerticalPanel();
    articlePanel.add(backLinksPanel);
    articlePanel.setCellWidth(backLinksPanel, "100%");
    backLinksPanel.setWidth("100%");
    
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
    
    topicHyperlink = new Anchor("www.adligo.org/org_docs/index#topic/article");
    topicHyperlink.setWordWrap(false);
    backlinkInnerPanel.add(topicHyperlink);
    
    Label lblNewLabel_3 = new Label(CONSTANTS.getArticle());
    lblNewLabel_3.setStyleName("articleSection");
    lblNewLabel_3.setWordWrap(false);
    backlinkInnerPanel.add(lblNewLabel_3);
    
    articleHyperlink = new Anchor("www.adligo.org/org_docs/index#article/0");
    backlinkInnerPanel.add(articleHyperlink);
    
    subArticlesParentPanel_ = new SimplePanel();
    articlePanel.add(subArticlesParentPanel_);
    subArticlesParentPanel_.setHeight("0px");
    
    SimplePanel articleRightIndentPanel = new SimplePanel();
    middleHorizontalPanel.add(articleRightIndentPanel);
    middleHorizontalPanel.setCellHorizontalAlignment(articleRightIndentPanel, HasHorizontalAlignment.ALIGN_RIGHT);
    articleRightIndentPanel.setWidth("4px");
    
    HorizontalPanel bottomHorizontalPanel = new HorizontalPanel();
    verticalPanel.add(bottomHorizontalPanel);
    bottomHorizontalPanel.setWidth("100%");
    verticalPanel.setCellVerticalAlignment(bottomHorizontalPanel, HasVerticalAlignment.ALIGN_BOTTOM);
    
    VerticalPanel verticalPanel_2 = new VerticalPanel();
    bottomHorizontalPanel.add(verticalPanel_2);
    verticalPanel_2.setWidth("100%");
    bottomHorizontalPanel.setCellWidth(verticalPanel_2, "100%");
    
    Label navigationLabel = new Label(CONSTANTS.getNavigation());
    navigationLabel.setStyleName("articleSection");
    verticalPanel_2.add(navigationLabel);
    verticalPanel_2.setCellHorizontalAlignment(navigationLabel, HasHorizontalAlignment.ALIGN_CENTER);
    
    upAnchor = new Anchor(CONSTANTS.getUp());
    upAnchor.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
      }
    });
    upAnchor.setWordWrap(false);
    upAnchor.setStyleName("articleSection");
    verticalPanel_2.add(upAnchor);
    verticalPanel_2.setCellHorizontalAlignment(upAnchor, HasHorizontalAlignment.ALIGN_CENTER);
    
    SimplePanel simplePanel_2 = new SimplePanel();
    verticalPanel_2.add(simplePanel_2);
    verticalPanel_2.setCellHorizontalAlignment(simplePanel_2, HasHorizontalAlignment.ALIGN_CENTER);
    
    HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
    horizontalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    simplePanel_2.setWidget(horizontalPanel_1);
    horizontalPanel_1.setSize("", "");
    
    previousAnchor = new Anchor(CONSTANTS.getPrevious());
    previousAnchor.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
      }
    });
    previousAnchor.setStyleName("articleSection");
    horizontalPanel_1.add(previousAnchor);
    horizontalPanel_1.setCellHorizontalAlignment(previousAnchor, HasHorizontalAlignment.ALIGN_RIGHT);
    
    SimplePanel simplePanel_1 = new SimplePanel();
    horizontalPanel_1.add(simplePanel_1);
    horizontalPanel_1.setCellHorizontalAlignment(simplePanel_1, HasHorizontalAlignment.ALIGN_CENTER);
    horizontalPanel_1.setCellVerticalAlignment(simplePanel_1, HasVerticalAlignment.ALIGN_MIDDLE);
    simplePanel_1.setWidth("10px");
    
    nextAnchor = new Anchor(CONSTANTS.getNext());
    nextAnchor.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
      }
    });
    nextAnchor.setStyleName("articleSection");
    horizontalPanel_1.add(nextAnchor);
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
    topicHyperlink.setText(url);
    topicHyperlink.setHref(url);
  }

  @Override
  public void setArticleUrl(String url) {
    articleHyperlink.setText(url);
    articleHyperlink.setHref(url);
  }

  @Override
  public void setUpdated(String niceDate) {
    dateLabel.setText(niceDate);
  }

  @Override
  public void setUpEnabled(boolean up) {
     upAnchor.setVisible(up);
  }

  @Override
  public void setPreviousEnabled(boolean p) {
    previousAnchor.setVisible(p);
    if (p) {
      if (previousArticleVersionView_ == null) {
        previousArticleVersionView_ = new PreviousArticleVersionView();
      }
      previousVersionPanel_.add(previousArticleVersionView_);
    } else {
      if (previousArticleVersionView_ != null) {
        previousVersionPanel_.clear();
      }
    }
  }

  @Override
  public void setNextEnabled(boolean n) {
    nextAnchor.setVisible(n);
  }


  @Override
  public void setChildArticles(int [] articleTreePath, List<I_ArticleBrief> articles) {
    int height = articles.size() * 30;
    subArticlesParentPanel_.setHeight("0px");
    
  }

  @Override
  public void setWidth(I_Dimension width) {
    String widthString = width.getDimension();
    abs.setWidth(widthString);
    verticalPanel.setWidth(widthString);
    super.setWidth(width);
  }

  @Override
  public void setHeight(I_Dimension height) {
    double heightDim = height.getDim();
    
    
    
    double images = heightDim/360;

    
    String heightString = height.getDimension();
    abs.setHeight(heightString);
    verticalPanel.setHeight(heightString);
    super.setHeight(height);
  }

  @Override
  public void setLastModified(String lastModified) {
    dateLabel.setText(lastModified);
  }

  @Override
  public void setTitle(String title) {
    titleLabel.setText(title);
  }

  @Override
  public void setPreviousVersion(String previousDate) {
    previousArticleVersionView_.setText(previousDate);
  }

}
