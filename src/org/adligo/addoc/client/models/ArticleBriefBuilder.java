package org.adligo.addoc.client.models;

import org.adligo.addoc.client.i18n.OneHundredArticleBriefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleBriefBuilder {

  public static List<I_ArticleBrief> buildBriefs(OneHundredArticleBriefs briefs, int start, int end) {
    List<I_ArticleBrief> toRet = new ArrayList<I_ArticleBrief>();
    
    for (int i = start; i < end; i++) {
      addArticle(briefs, i, toRet);
    }
    return toRet;
  }
  
  private static void addArticle(OneHundredArticleBriefs lookup, int id, List<I_ArticleBrief> map) {
    ArticleBriefMutant am = new ArticleBriefMutant();
    String name = lookup.getString("getA" + id + "Name");
    am.setName(name);
    am.setId(id);
    
    String originalId = lookup.getString("getA" + id + "Previous");
    if (originalId.trim().length() != 0) {
      int oid = new Integer(originalId);
      am.setPreviousId(oid);
    }
     
    map.add(new ArticleBrief(am));
  }
  
}
