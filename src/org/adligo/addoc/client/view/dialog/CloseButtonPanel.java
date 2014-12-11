package org.adligo.addoc.client.view.dialog;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class CloseButtonPanel extends Composite {
  private Button closeButton;
  
  public CloseButtonPanel() {    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    initWidget(horizontalPanel);
    
    closeButton = new Button();
    closeButton.setText("Close");
    horizontalPanel.add(closeButton);
    horizontalPanel.setCellHorizontalAlignment(closeButton, HasHorizontalAlignment.ALIGN_RIGHT);
  }
  
  public void addCloseHandler(ClickHandler clickandler) {
    closeButton.addClickHandler(clickandler);
  }
  
  public void setCloseText(String text) {
    closeButton.setText(text);
  }
}
