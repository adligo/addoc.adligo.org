package org.adligo.addoc.client.models;

import com.google.gwt.i18n.client.ConstantsWithLookup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleTreesBuilder {
  
  public static Map<Integer, I_ArticleTree> buildTrees(ConstantsWithLookup lookup, int startId, int endId) {
    Map<Integer, I_ArticleTree> toRet = new HashMap<Integer, I_ArticleTree>();
    for (int i = startId; i <= endId; i++) {
      
      I_ArticleTree result = addTree(lookup, i);
      toRet.put(i,  result);
    }
    return toRet;
  }
  
  private static I_ArticleTree addTree(ConstantsWithLookup lookup, int id) {
    ArticleTreeMutant tree = new ArticleTreeMutant();
    String date = lookup.getString("getTree" + id + "Date");
    tree.setDate(date);
    String articleTree = lookup.getString("getTree" + id);
    I_NumberedTree nt = new NumberedTree(articleTree);
    tree.setNumberedTree(nt);
    return new ArticleTree(tree);
  }
  
}
