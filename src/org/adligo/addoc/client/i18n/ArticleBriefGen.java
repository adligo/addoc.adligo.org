package org.adligo.addoc.client.i18n;

public class ArticleBriefGen {

  public static final void main(String [] args) {
    for (int i = 0; i < 100; i++) {
      System.out.println("  public String getA" + i + "Name();");
      System.out.println("  public String getA" + i + "Previous();");
      System.out.println("");
    }
    for (int i = 0; i < 100; i++) {
      System.out.println("getA" + i + "Name=");
      System.out.println("getA" + i + "Previous=");
      System.out.println("");
    }
  }
}
