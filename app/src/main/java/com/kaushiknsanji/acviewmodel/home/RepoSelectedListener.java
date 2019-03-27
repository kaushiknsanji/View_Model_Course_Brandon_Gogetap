package com.kaushiknsanji.acviewmodel.home;

import com.kaushiknsanji.acviewmodel.model.Repo;

/**
 * Listener interface to be implemented by the {@link ListFragment}
 * to receive event callbacks for user actions on the list of Repositories shown.
 */
public interface RepoSelectedListener {

    /**
     * Callback Method of {@link RepoSelectedListener} invoked when
     * the user clicks on a Repo item in the list of Repositories shown.
     *
     * @param repo The {@link Repo} data of the item clicked
     */
    void onRepoSelected(Repo repo);
}
