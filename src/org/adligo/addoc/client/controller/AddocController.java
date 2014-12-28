package org.adligo.addoc.client.controller;

import org.adligo.addoc.client.bi.I_Browser;
import org.adligo.addoc.client.presenter.MainPresenter;
import org.adligo.addoc.client.presenter.content.I_ContentManager;
import org.adligo.addoc.client.view.ViewFactory;
import org.adligo.css.shared.models.I_StyleSheet;

public class AddocController {
  private MainPresenter main;
  
  public AddocController(I_StyleSheet styleSheet, I_Browser browser, I_ContentManager config) {
    main = new MainPresenter(new ViewFactory(styleSheet), browser, config);
    
  }
}
