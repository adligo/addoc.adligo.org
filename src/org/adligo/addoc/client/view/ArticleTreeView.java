package org.adligo.addoc.client.view;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionModel.AbstractSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import org.adligo.addoc.client.ui.I_ArticleTreeView;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class ArticleTreeView extends Composite implements I_ArticleTreeView {
  private DialogBox dialog;
  private TreeNavigationView navView_;
  
  public ArticleTreeView() {
    
    AbsolutePanel absolutePanel = new AbsolutePanel();
    initWidget(absolutePanel);
    absolutePanel.setSize("450px", "450px");
    
    CellTree cellTree = new CellTree(
      new TreeViewModel() {
        final AbstractDataProvider<String> dataProvider = new ListDataProvider<String>();
        final AbstractSelectionModel<String> selectionModel = new NoSelectionModel<String>();
        @Override
        public <T> NodeInfo<?> getNodeInfo(T value) {
          return new DefaultNodeInfo<String>(dataProvider, new TextCell(), selectionModel, null);
        }
        @Override
        public boolean isLeaf(Object value) {
          return true;
        }
      }, null);
    absolutePanel.add(cellTree);
    cellTree.setSize("450px", "450px");
    
    SimplePanel simplePanel = new SimplePanel();
    absolutePanel.add(simplePanel, 294, 10);
    simplePanel.setSize("125px", "70px");
    navView_ = new TreeNavigationView();
    navView_.setup();
    simplePanel.add(navView_);
    
    Image image = new Image("images/close.png");
    image.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        hide();
      }
    });
    absolutePanel.add(image, 416, 10);
    image.setSize("24px", "24px");
    
    
  }
  
  public void show() {
    if (dialog == null) {
      dialog = new DialogBox();
      dialog.add(this);
      dialog.setPopupPosition(15, 15);
    }
    dialog.show();
  }
  
  public void hide() {
    dialog.hide();
  }
  
  
}
