package org.adligo.addoc.client.i18n;

public class ArticleTreeGen {

  public static final void main(String [] args) {
    for (int i = 0; i < 100; i++) {
      System.out.println("  public String getTree" + i + "();");
      System.out.println("  public String getTree" + i + "Date();");
      System.out.println("");
    }
    for (int i = 0; i < 100; i++) {
      System.out.println("getTree" + i + "Date=");
      System.out.println("getTree" + i + "=");
      System.out.println("");
    }
  }
}
