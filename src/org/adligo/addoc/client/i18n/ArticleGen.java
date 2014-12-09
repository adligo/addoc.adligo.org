package org.adligo.addoc.client.i18n;

public class ArticleGen {

  public static final void main(String [] args) {
    for (int i = 0; i < 100; i++) {
      System.out.println("  public String getA" + i + "Date();");
      System.out.println("  public String getA" + i + "Name();");
      System.out.println("  public String getA" + i + "Content();");
      System.out.println("  public String getA" + i + "Original();");
      System.out.println("");
    }
    for (int i = 0; i < 100; i++) {
      System.out.println("getA" + i + "Date=");
      System.out.println("getA" + i + "Name=");
      System.out.println("getA" + i + "Content=");
      System.out.println("getA" + i + "Original=");
      System.out.println("");
    }
  }
}
