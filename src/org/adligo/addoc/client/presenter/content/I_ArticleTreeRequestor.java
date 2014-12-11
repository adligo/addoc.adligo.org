package org.adligo.addoc.client.presenter.content;

import org.adligo.addoc.client.models.I_ArticleTree;

/**
 * note this is like a service in that it proxys a call to the
 * server, however it is only calling out to i18n property files
 * so I didn't put it in a service package (since it doesn't call a Servlet).
 * @author scott
 *
 */
public interface I_ArticleTreeRequestor {
  public void onSuccess(I_ArticleTree tree);
  public void onFailure(Throwable caught);
}
