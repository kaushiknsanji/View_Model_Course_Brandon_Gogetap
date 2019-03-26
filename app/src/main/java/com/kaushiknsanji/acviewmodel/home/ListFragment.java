package com.kaushiknsanji.acviewmodel.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kaushiknsanji.acviewmodel.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ListFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_error)
    TextView mTextViewError;

    @BindView(R.id.loading_view)
    ProgressBar mProgressBarLoadingView;

    private Unbinder mUnbinder;

    private ListViewModel mListViewModel;

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screen_list, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Get the ListViewModel instance
        mListViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        //Register the data observers on ListViewModel
        observeListViewModel();
    }

    private void observeListViewModel() {
        mListViewModel.getRepos().observe(this, repos -> {
            if (repos != null) {
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        //Register for loading error status
        mListViewModel.getRepoLoadError().observe(this, isError -> {
            //noinspection ConstantConditions
            if (isError) {
                //On Error, show the Error TextView with an Error message
                mTextViewError.setVisibility(View.VISIBLE);
                mTextViewError.setText(R.string.api_error_repos);
                //Hide the RecyclerView
                mRecyclerView.setVisibility(View.GONE);
            } else {
                //On NO Error, hide the Error TextView and clear the Error message previously set
                mTextViewError.setVisibility(View.GONE);
                mTextViewError.setText(null);
            }
        });

        //Register for loading status
        mListViewModel.getLoading().observe(this, isLoading -> {
            //Show the Progress when loading
            //noinspection ConstantConditions
            mProgressBarLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (isLoading) {
                //When loading, hide the Error TextView and the RecyclerView
                mTextViewError.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Called when the view previously created by {@link #onCreateView} has
     * been detached from the fragment.  The next time the fragment needs
     * to be displayed, a new view will be created.  This is called
     * after {@link #onStop()} and before {@link #onDestroy()}.  It is called
     * <em>regardless</em> of whether {@link #onCreateView} returned a
     * non-null view.  Internally it is called after the view's state has
     * been saved but before it has been removed from its parent.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            //Unbinding the Views when the Fragment View is detached, to prevent leaks
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

}
