package com.kaushiknsanji.acviewmodel.home;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import com.kaushiknsanji.acviewmodel.model.Repo;

import java.util.List;

/**
 * Class that extends {@link DiffUtil.Callback}
 * to analyse the difference between two lists of {@link Repo} objects,
 * for triggering necessary updates to RecyclerView's Adapter to load the changes.
 *
 * @author Kaushik N Sanji
 */
public class RepoDiffCallback extends DiffUtil.Callback {

    //For the current/old list of Repositories
    private final List<Repo> mOldRepoList;

    //For the new list of Repositories
    private final List<Repo> mNewRepoList;

    /**
     * Constructor of {@link RepoDiffCallback}
     *
     * @param oldRepoList The Current/Old list of {@link Repo} objects
     * @param newRepoList The New list of {@link Repo} objects
     */
    RepoDiffCallback(List<Repo> oldRepoList, List<Repo> newRepoList) {
        mOldRepoList = oldRepoList;
        mNewRepoList = newRepoList;
    }

    /**
     * Returns the size of the old list.
     *
     * @return The size of the old list.
     */
    @Override
    public int getOldListSize() {
        return mOldRepoList.size();
    }

    /**
     * Returns the size of the new list.
     *
     * @return The size of the new list.
     */
    @Override
    public int getNewListSize() {
        return mNewRepoList.size();
    }

    /**
     * Called by the DiffUtil to decide whether two object represent the same Item.
     * <p>
     * For example, if your items have unique ids, this method should check their id equality.
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list
     * @return True if the two items represent the same object or false if they are different.
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        //Returning the result of the comparison of the Repository Id
        return mOldRepoList.get(oldItemPosition).getId() == mNewRepoList.get(newItemPosition).getId();
    }

    /**
     * Called by the DiffUtil when it wants to check whether two items have the same data.
     * DiffUtil uses this information to detect if the contents of an item has changed.
     * <p>
     * DiffUtil uses this method to check equality instead of {@link Object#equals(Object)}
     * so that you can change its behavior depending on your UI.
     * For example, if you are using DiffUtil with a
     * {@link RecyclerView.Adapter RecyclerView.Adapter}, you should
     * return whether the items' visual representations are the same.
     * <p>
     * This method is called only if {@link #areItemsTheSame(int, int)} returns
     * {@code true} for these items.
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list which replaces the
     *                        oldItem
     * @return True if the contents of the items are the same or false if they are different.
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        //Returning the result of the comparison of the Repository contents
        return mOldRepoList.get(oldItemPosition).equals(mNewRepoList.get(newItemPosition));
    }
}