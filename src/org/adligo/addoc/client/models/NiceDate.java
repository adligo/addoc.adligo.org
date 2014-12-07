package org.adligo.addoc.client.models;

import com.google.gwt.i18n.client.DateTimeFormat;

public class NiceDate {
  private static final DateTimeFormat FORMAT = DateTimeFormat.getFormat("yyyy.mm.dd");
  
  public static long parse(String niceDate) {
    return FORMAT.parse(niceDate).getTime();
  }
}
