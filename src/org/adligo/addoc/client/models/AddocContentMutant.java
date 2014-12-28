package org.adligo.addoc.client.models;

import org.adligo.addoc.client.bi.I_Browser;
import org.adligo.css.shared.models.I_StyleSheet;
import org.adligo.css.shared.models.selectors.Selector;
import org.adligo.css.shared.models.selectors.SequenceOfSimpleSelectors;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class AddocContentMutant implements I_AddocContent {
  public static final String ADDOC_CONTENT_MUTANT_REQUIRES_A_BROWSER = "AddocContentMutant requires a browser.";
  private I_SimplePanelContent titleImage_;
  private I_SimplePanelContent headerAd_;
  private I_SimplePanelContent [] rightFrames_;
  private I_SimplePanelContent [] leftFrames_;
  private I_ContentCache contentCache_;
  private I_Browser browser_;
  
  public AddocContentMutant() {}
  
  public AddocContentMutant(I_ContentCache cache, I_StyleSheet styleSheet, I_Browser browser) {
    setContentCache(cache);
    if (browser == null) {
      throw new IllegalArgumentException(ADDOC_CONTENT_MUTANT_REQUIRES_A_BROWSER);
    }
    browser_ = browser;
    titleImage_ = parseCssSimpleContentClass(styleSheet,"menuTitleImage");
    if (styleSheet.hasSelector(new Selector(new SequenceOfSimpleSelectors("menuLeaderboardAd")))) {
      headerAd_ = parseCssSimpleContentClass(styleSheet, "menuLeaderboardAd");
    }
    String rightColumnAds = styleSheet.getValue(new Selector(new SequenceOfSimpleSelectors("rightColumAds")),"ad-list"); 
    if (rightColumnAds != null) {
      rightFrames_ = readAdArray(styleSheet, rightColumnAds);
    }
    String leftColumnAds = styleSheet.getValue(new Selector(new SequenceOfSimpleSelectors("leftColumAds")),"ad-list"); 
    if (leftColumnAds != null) {
      leftFrames_ = readAdArray(styleSheet, leftColumnAds);
    }
  }

  private I_SimplePanelContent [] readAdArray(I_StyleSheet styleSheet, String adNames) {
    List<I_SimplePanelContent> ads = new ArrayList<I_SimplePanelContent>();
    char [] chars = adNames.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      if (c == ',') {
        String token = sb.toString().trim();
        sb = new StringBuilder();
        ads.add(parseCssSimpleContentClass(styleSheet, token));
      } else {
        sb.append(c);
      }
    }
    String token = sb.toString().trim();
    if (token.length() >= 1) {
      ads.add(parseCssSimpleContentClass(styleSheet, token));
    }
    I_SimplePanelContent [] adArray = new I_SimplePanelContent[ads.size()];
    for (int i = 0; i < adArray.length; i++) {
      adArray[i] = ads.get(i);
    }
    return adArray;
  }
  
  private I_SimplePanelContent parseCssSimpleContentClass(I_StyleSheet styleSheet, String cssClassName) {
    Selector selector = new Selector(new SequenceOfSimpleSelectors(cssClassName));
    String url = null;
    if (styleSheet.hasValue(selector, "url")) {
      url = styleSheet.getValue(selector, "url");
    }
    String height = styleSheet.getValue(selector, "height");
    String width = styleSheet.getValue(selector, "width");
    return new SimplePanelContent(height, width, url);
  }
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_AddocContent#getHeaderImage()
   */
  @Override
  public I_SimplePanelContent getTitleImage() {
    return titleImage_;
  }
  public void setTitleImage(I_SimplePanelContent headerImage) {
    this.titleImage_ = headerImage;
  }
  /* (non-Javadoc)
   * @see org.adligo.addoc.client.models.I_AddocContent#getHeaderAd()
   */
  @Override
  public I_SimplePanelContent getHeaderAd() {
    return headerAd_;
  }
  public void setHeaderAd(I_SimplePanelContent headerAd) {
    this.headerAd_ = headerAd;
  }
  public I_SimplePanelContent[] getRightFrames() {
    return rightFrames_;
  }
  public void setRightAds(I_SimplePanelContent[] rightAds) {
    this.rightFrames_ = rightAds;
  }
  public I_SimplePanelContent[] getLeftFrames() {
    return leftFrames_;
  }
  public void setLeftAds(I_SimplePanelContent[] leftAds) {
    this.leftFrames_ = leftAds;
  }
  public I_ContentCache getContentCache() {
    return contentCache_;
  }
  public void setContentCache(I_ContentCache contentCache) {
    this.contentCache_ = contentCache;
  }  
}
