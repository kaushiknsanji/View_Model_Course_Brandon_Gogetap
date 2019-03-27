package com.kaushiknsanji.acviewmodel.home;


import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaushiknsanji.acviewmodel.R;
import com.kaushiknsanji.acviewmodel.model.Repo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    //Data of the Adapter which is a list of Repositories
    private final List<Repo> mRepoList = new ArrayList<>();

    //Listener instance that receives callback events on user actions
    private final RepoSelectedListener mRepoSelectedListener;

    /**
     * Constructor of {@link RepoListAdapter}
     *  @param viewModel      Instance of {@link ListViewModel}
     * @param lifecycleOwner Instance of {@link LifecycleOwner} that needs to receive the events
     *                       published by the {@link ListViewModel}
     * @param repoSelectedListener Instance of {@link RepoSelectedListener} to receive callback events on user actions
     */
    RepoListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner, RepoSelectedListener repoSelectedListener) {
        //Register for loading Repositories
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            //Clear data initially
            mRepoList.clear();
            if (repos != null) {
                //Load new data when available
                mRepoList.addAll(repos);
                //Trigger the data change event
                notifyDataSetChanged(); //TODO: Use DiffUtil with AutoValue models later
            }
        });

        mRepoSelectedListener = repoSelectedListener;

        //All Items have stable Ids
        setHasStableIds(true);
    }


    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int, List)}.
     * Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the item layout 'R.layout.item_repo_list'
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo_list, parent, false);
        //Returning the ViewHolder instance for the inflated Item View
        return new ViewHolder(itemView);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link android.widget.ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int, List)}
     * instead if Adapter can handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Bind the data to the item view at the position
        holder.bind(mRepoList.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    /**
     * Return the stable ID for the item at <code>position</code>. If {@link #hasStableIds()}
     * would return false this method should return {@link RecyclerView#NO_ID}. The default implementation
     * of this method returns {@link RecyclerView#NO_ID}.
     *
     * @param position Adapter position to query
     * @return the stable ID of the item at position
     */
    @Override
    public long getItemId(int position) {
        return mRepoList.get(position).getId();
    }

    /**
     * ViewHolder class for caching View components of the template item view 'R.layout.item_repo_list'
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_repo_name)
        TextView mTextViewRepoName;

        @BindView(R.id.tv_repo_description)
        TextView mTextViewRepoDescription;

        @BindView(R.id.tv_stars)
        TextView mTextViewStars;

        @BindView(R.id.tv_forks)
        TextView mTextViewForks;

        //Reference to the data at the item position received during bind
        private Repo mItemRepo;

        /**
         * Constructor of {@link ViewHolder}
         *
         * @param itemView Inflated instance of the Item View 'R.layout.item_repo_list'
         */
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //Register click listener on the Item View
            itemView.setOnClickListener(view -> {
                if (mItemRepo != null) {
                    mRepoSelectedListener.onRepoSelected(mItemRepo);
                }
            });
        }

        /**
         * Method that binds the views with the data {@code repo} at the position
         *
         * @param repo The {@link Repo} data at the Item position
         */
        void bind(Repo repo) {
            //Store a reference to the data at the position
            mItemRepo = repo;
            //Bind the views to the data at the position
            mTextViewRepoName.setText(repo.getName());
            mTextViewRepoDescription.setText(repo.getDescription());
            mTextViewStars.setText(String.valueOf(repo.getStars()));
            mTextViewForks.setText(String.valueOf(repo.getForks()));
        }
    }
}
