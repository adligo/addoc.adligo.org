package org.adligo.addoc.client.models;


public class ArticleBuilder {
  String date_ = null;
  Integer height_ = null;
  
  public I_Article build(I_ArticleBrief brief, String text) {
    date_ = null;
    height_ = null;
    
    StringBuilder sb = new StringBuilder();
    char [] chars = text.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      if (c == '\n') {
        String line = sb.toString();
        if (line.length() >= 0) {
          if (addContent(line)) {
            sb = new StringBuilder();
          }
        }
        
      } else if (c == '\r') {
        String line = sb.toString();
        if (line.length() >= 0) {
          if (addContent(line)) {
            sb = new StringBuilder();
          }
        }
      } else {
        sb.append(c);
      }
    }
    ArticleMutant am = new ArticleMutant(brief);
    am.setDate(date_);
    am.setHeight(height_);
    am.setContent(sb.toString());
    return new Article(am);
    
  }
  
  private boolean addContent(String line) {
    if (date_ == null) {
      if (line.indexOf("date=") != 0) {
        throw new IllegalArgumentException("Articles must have date= in the first line.");
      }  else {
         date_ = line.substring(5, line.length());
         return true;
      }
      
    } else if (height_ == null) {
      if (line.indexOf("height=") != 0) {
        throw new IllegalArgumentException("Articles must have height= in the first line.");
      }  else {
        String heightString = line.substring(7, line.length());
        height_ = new Integer(heightString);
        return true;
      }
    }
    return false;
  }
}
