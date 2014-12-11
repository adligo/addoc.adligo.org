package org.adligo.addoc.client.view.dialog;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.adligo.addoc.client.i18n.AddocI18nConstants;
import org.adligo.addoc.client.ui.I_DialogView;

public class DialogView extends Composite implements I_DialogView {
  private static final AddocI18nConstants CONSTANTS = GWT.create(AddocI18nConstants.class);
  private static final String DEFAULT_HEIGHT = "120px";
  private static final String DEFAULT_WIDTH = "500px";
  private String height_ = DEFAULT_HEIGHT;
  private String width_ = DEFAULT_WIDTH;
  
  private DialogBox dialog_;
  private Label textLabel_;
  private AbsolutePanel buttonPanel_;
  private CloseButtonPanel closeButtonPanel_;
  private TrueFalseButtonPanel trueFalseButtonPanel_;
  private AbsolutePanel mainPanel_;
  
  public DialogView() {
    mainPanel_ = new AbsolutePanel();
    mainPanel_.setStyleName("dialogView");
    initWidget(mainPanel_);
    
    Image image = new Image("images/Backround.png");
    mainPanel_.add(image);
    image.setSize("100%", "100%");
    
    VerticalPanel verticalPanel = new VerticalPanel();
    mainPanel_.add(verticalPanel, 0, 0);
    verticalPanel.setSize("100%", "100%");
    
    textLabel_ = new Label("New label");
    verticalPanel.add(textLabel_);
    verticalPanel.setCellVerticalAlignment(textLabel_, HasVerticalAlignment.ALIGN_MIDDLE);
    verticalPanel.setCellHorizontalAlignment(textLabel_, HasHorizontalAlignment.ALIGN_CENTER);
    
    buttonPanel_ = new AbsolutePanel();
    verticalPanel.add(buttonPanel_);
    verticalPanel.setCellWidth(buttonPanel_, "100%");
    verticalPanel.setCellHorizontalAlignment(buttonPanel_, HasHorizontalAlignment.ALIGN_CENTER);
    buttonPanel_.setSize("100%", "30px");
  }

  @Override
  public void show() {
    if (dialog_ == null) {
      dialog_ = new DialogBox();
      dialog_.setWidget(this);
      dialog_.setModal(false);
      
      dialog_.setHeight(height_);
      dialog_.setWidth(width_);
      
    }
    mainPanel_.setHeight(height_);
    mainPanel_.setWidth(width_);
    
    dialog_.center();
    dialog_.show();
  }

  @Override
  public void hide() {
    dialog_.hide();
  }

  @Override
  public void setTrueFalseButtonsVisible() {
    if (trueFalseButtonPanel_ == null) {
      trueFalseButtonPanel_ = new TrueFalseButtonPanel();
    }
    buttonPanel_.clear();
    buttonPanel_.add(trueFalseButtonPanel_);
  }

  @Override
  public void setTrueButtonText(String text) {
    //TODO
  }

  @Override
  public void setFalseButtonText(String text) {
    //TODO
  }

  @Override
  public void setCloseButtonVisible() {
    getCloseButtonPanel();
    buttonPanel_.clear();
    buttonPanel_.add(closeButtonPanel_);
  }
  
  /**
   * lazy initilization
   * @return
   */
  public CloseButtonPanel getCloseButtonPanel() {
    if (closeButtonPanel_ == null) {
      closeButtonPanel_ = new CloseButtonPanel();
      closeButtonPanel_.addCloseHandler(new ClickHandler() {
        
        @Override
        public void onClick(ClickEvent event) {
          dialog_.hide();
        }
      });
    }
    return closeButtonPanel_;
  }

  @Override
  public void setCloseButtonText(String text) {
    CloseButtonPanel closeButtonPanel = getCloseButtonPanel();
    closeButtonPanel.setCloseText(text);
  }

  @Override
  public void setMessageText(String text, boolean wordwrap) {
    textLabel_.setWordWrap(wordwrap);
    textLabel_.setText(text);
  }

  public String getHeight() {
    return height_;
  }

  public void setHeight(String height) {
    height_ = height;
  }

  public String getWidth() {
    return width_;
  }

  public void setWidth(String width) {
    width_ = width;
  }
  
  /**
   * Close button with default height, width.
   */
  public void resetDefaults() {
    setCloseButtonVisible();
    setHeight(DEFAULT_HEIGHT);
    setWidth(DEFAULT_WIDTH);
  }
}
