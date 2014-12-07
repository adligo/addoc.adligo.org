package org.adligo.addoc_example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.adligo.addoc.client.controller.AddocController;
import org.adligo.addoc.client.models.AddocContentMutant;
import org.adligo.addoc.client.models.SimplePanelContent;

/**
 * This is a example usage of the addoc.adligo.org code
 * to display a simple documentation with history,
 * url link history support, navigation and
 * adds (they make money!).
 * 
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ExampleEntryPoint implements EntryPoint {

  public void onModuleLoad() {
   AddocContentMutant content = new AddocContentMutant();
   SimplePanelContent titleImage = new SimplePanelContent(90, 500, "images/beeTitle.png");
   content.setTitleImage(titleImage);
   SimplePanelContent headerAd = new SimplePanelContent(90, 728, "adds/bidvertiser.com/leaderboard.html");
   content.setHeaderAd(headerAd);
   
   new AddocController(content);
  
  }
}
