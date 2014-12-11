package org.adligo.addoc.client.view.dialog;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.event.dom.client.ClickHandler;

public class TrueFalseButtonPanel extends Composite {
  private Button falseButton;
  private Button trueButton;
  public TrueFalseButtonPanel() {
    
    HorizontalPanel horizontalPanel = new HorizontalPanel();
    initWidget(horizontalPanel);
    
    falseButton = new Button("New button");
    falseButton.setText("False");
    horizontalPanel.add(falseButton);
    horizontalPanel.setCellHorizontalAlignment(falseButton, HasHorizontalAlignment.ALIGN_RIGHT);
    
    SimplePanel simplePanel = new SimplePanel();
    horizontalPanel.add(simplePanel);
    simplePanel.setWidth("10px");
    
    trueButton = new Button("New button");
    trueButton.setText("True");
    horizontalPanel.add(trueButton);
  }
  
  public void addTrueButtonHandler(ClickHandler clickandler) {
    trueButton.addClickHandler(clickandler);
  }
  
  public void addFalseButtonHandler(ClickHandler clickandler) {
    falseButton.addClickHandler(clickandler);
  }

  public void setTrueButtonText(String text) {
    trueButton.setText(text);
  }
  public void setFalseButtonText(String text) {
    falseButton.setText(text);
  }
}
