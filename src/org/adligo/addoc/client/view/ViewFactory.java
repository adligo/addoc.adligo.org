package org.adligo.addoc.client.view;

import org.adligo.addoc.client.ui.I_AdColumnView;
import org.adligo.addoc.client.ui.I_ArticleTreeView;
import org.adligo.addoc.client.ui.I_ArticleView;
import org.adligo.addoc.client.ui.I_DialogView;
import org.adligo.addoc.client.ui.I_MainView;
import org.adligo.addoc.client.ui.I_MenuView;
import org.adligo.addoc.client.ui.I_ViewFactory;
import org.adligo.addoc.client.view.dialog.DialogView;

public class ViewFactory implements I_ViewFactory {
  private DialogView dialogView_;
  
  @Override
  public I_MainView createMainView() {
    return new MainView();
  }

  @Override
  public I_MenuView createMenuView() {
    return new MenuView();
  }

  @Override
  public I_AdColumnView createAdColumnView() {
    return new AdColumnView();
  }

  @Override
  public I_ArticleView createArticleView() {
    return new ArticleView();
  }

  @Override
  public I_ArticleTreeView createArticleTreeView() {
    return new ArticleTreeView();
  }

  @Override
  public I_DialogView createDialogView() {
    if (dialogView_ == null) {
      dialogView_ = new DialogView();
    }
    return dialogView_;
  }

}
