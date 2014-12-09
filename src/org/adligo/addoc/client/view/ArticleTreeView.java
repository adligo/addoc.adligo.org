package org.adligo.addoc.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import org.adligo.addoc.client.i18n.AddocI18nConstants;
import org.adligo.addoc.client.ui.I_ArticleTreeView;
import org.adligo.addoc.client.ui.handlers.I_AddocHandler;

import java.util.ArrayList;
import java.util.List;

public class ArticleTreeView extends Composite implements I_ArticleTreeView {
  private DialogBox dialog;
  private TreeNavigationView navView_;
  private Tree tree_;
  private I_AddocHandler handler_;
  
  public ArticleTreeView() {
    
    AbsolutePanel absolutePanel = new AbsolutePanel();
    initWidget(absolutePanel);
    absolutePanel.setSize("450px", "450px");
    
    tree_ = new Tree();
    tree_.setTitle(((AddocI18nConstants) GWT.create(AddocI18nConstants.class)).getIndex());
    tree_.addSelectionHandler(new SelectionHandler<TreeItem>() {
      
      @Override
      public void onSelection(SelectionEvent<TreeItem> event) {
        onTreeSelection(event.getSelectedItem());
      }
    });
    absolutePanel.add(tree_);
    tree_.setSize("450px", "450px");
    
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
    absolutePanel.add(image, 434, 4);
    image.setSize("16px", "16px");
    
    
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

  @Override
  public void clearNodes() {
    tree_.clear();
  }
  @Override
  public void addNode(String[] nodePath) {
    int items = tree_.getItemCount();
    String topPathPart = nodePath[0];
    
    TreeItem topNode = null;
    for (int j = 0; j < items; j++) {
      TreeItem nextNode = tree_.getItem(j);
      Label topLabel = (Label) nextNode.getWidget();
      if (topPathPart.equals(topLabel.getText())) {
        topNode = nextNode;
        break;
      }
    }
    if (topNode == null) {
      topNode = tree_.addItem(new Label(topPathPart));
    }
    if (nodePath.length >= 2) {
      addChildNodes(nodePath, 1, topNode);
    }
  }

  private void addChildNodes(String [] nodePath, int pathPart, TreeItem parent) {
    int items = parent.getChildCount();
    String pathPartName = nodePath[pathPart];
    
    TreeItem node = null;
    for (int j = 0; j < items; j++) {
      TreeItem nextNode = parent.getChild(j);
      Label topLabel = (Label) nextNode.getWidget();
      if (pathPartName.equals(topLabel.getText())) {
        node = nextNode;
        break;
      }
    }
    if (node == null) {
      node = parent.addItem(new Label(pathPartName));
    }
    if (nodePath.length > pathPart + 1) {
      addChildNodes(nodePath, pathPart + 1, node);
    }
  }
  @Override
  public void setHandler(I_AddocHandler handler) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void showPreviousTreeLink(boolean show) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void showNextTreeLink(boolean show) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void showCurrentTreeLink(boolean show) {
    // TODO Auto-generated method stub
    
  }
  
  private void onTreeSelection(TreeItem treeItem) {
    List<String> path = new ArrayList<String>();
    Label label = (Label) treeItem.getWidget();
    path.add(label.getText());
    TreeItem parent = treeItem.getParentItem();
    while (parent != null) {
      label = (Label) parent.getWidget();
      path.add(label.getText());
      parent = parent.getParentItem();
    }
    //make the names top to bottom
    String [] pathNames = new String[path.size()];
    int counter = 0;
    for (int i = path.size() - 1; i >= 0; i--) {
      pathNames[counter++] = path.get(i);
    }
    handler_.onArticleClick(pathNames);
  }

  @Override
  public void setDateModified(String date) {
    navView_.setDateModified(date);
  }
  
}
