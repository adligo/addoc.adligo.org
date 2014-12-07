package org.adligo.addoc.client.controller;

import org.adligo.addoc.client.models.I_AddocContent;
import org.adligo.addoc.client.presenter.MainPresenter;
import org.adligo.addoc.client.view.ViewFactory;

public class AddocController {
  private MainPresenter main;
  
  public AddocController(I_AddocContent config) {
    main = new MainPresenter(new ViewFactory(), config);
    
  }
}
