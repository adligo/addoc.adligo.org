package org.adligo.addoc.client.presenter;

/**
 * This enum contains the initial load states of a article tree
 * and a article. 
 * 
 * @author scott
 *
 */
public enum ArticlePresenterRoot {
  /**
   * load the latest tree and it's default article
   */
    LoadLatestTreeWithDefaultArticle,
    /**
     * load a immutable article by it's id, and
     * then find the tree it was for using the article
     * article date must be before the tree date.
     */
    LoadTreeForImmutableArticle,
    /**
     * load the most current tree which contains
     * a matching topic path, where a topic path
     * is a directory like path structure 
     * represented by a article tree made up of
     * article names.
     */
    LoadTreeForTopic
}
