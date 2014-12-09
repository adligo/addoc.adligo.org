package org.adligo.addoc.client.models;

import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;

public class NiceDate {
  private DateTimeFormat format;
  
  public NiceDate(String formatString) {
    format = DateTimeFormat.getFormat(formatString);
  }
  
  public long parse(String niceDate) {
    return format.parse(niceDate).getTime();
  }
  
  public String format(long date) {
    return format.format(new Date(date));
  }
}
