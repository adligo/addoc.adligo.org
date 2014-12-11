package org.adligo.addoc.client.i18n;

/** 
 * a simple way to call GWT.create for a class literal later in 
 * a split code section.
 * @author scott
 *
 * @param <T>
 */
public interface GWTCreateWrapper<T> {
  public T create();
}
