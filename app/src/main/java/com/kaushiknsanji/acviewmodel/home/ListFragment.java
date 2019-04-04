package com.kaushiknsanji.acviewmodel.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kaushiknsanji.acviewmodel.R;
import com.kaushiknsanji.acviewmodel.base.BaseFragment;
import com.kaushiknsanji.acviewmodel.details.DetailsFragment;
import com.kaushiknsanji.acviewmodel.model.Repo;
import com.kaushiknsanji.acviewmodel.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * {@link android.support.v4.app.Fragment} that inflates the layout 'R.layout.screen_list' to load and display
 * a list of GitHub Repositories. This is a container fragment of {@link MainActivity}.
 *
 * @author Kaushik N Sanji
 */
public class ListFragment extends BaseFragment implements RepoSelectedListener {

    //Injected instance of the ViewModelFactory
    @Inject
    ViewModelFactory mViewModelFactory;

    //RecyclerView to display the list of Repositories
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    //TextView to display the error
    @BindView(R.id.tv_error)
    TextView mTextViewError;

    //ProgressBar to display the Progress circle
    @BindView(R.id.loading_view)
    ProgressBar mProgressBarLoadingView;

    //Butterknife's Unbinder instance
    private Unbinder mUnbinder;

    //ViewModel for the list of Repositories
    private ListViewModel mListViewModel;

    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Inject the ListFragment's dependencies
        getApplicationComponent().inject(this);
    }

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
        mListViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ListViewModel.class);
        //Set the Layout Manager for RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        //Add Item Decoration to RecyclerView Items
        mRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        //Set RecyclerView Adapter
        mRecyclerView.setAdapter(new RepoListAdapter(mListViewModel, this, this));
        //Register the data observers on ListViewModel
        observeListViewModel();
    }

    /**
     * Method that registers the data observers on ListViewModel
     */
    private void observeListViewModel() {
        //Register for loading Repositories
        mListViewModel.getRepos().observe(this, repos -> {
            if (repos != null) {
                //When Repositories are available, show the RecyclerView
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

    /**
     * Callback Method of {@link RepoSelectedListener} invoked when
     * the user clicks on a Repo item in the list of Repositories shown.
     *
     * @param repo The {@link Repo} data of the item clicked
     */
    @Override
    public void onRepoSelected(Repo repo) {
        //Get the SelectedRepoViewModel Instance
        SelectedRepoViewModel selectedRepoViewModel = ViewModelProviders.of(requireActivity(), mViewModelFactory).get(SelectedRepoViewModel.class);
        //Set the selected Repo on the ViewModel
        selectedRepoViewModel.setSelectedRepo(repo);
        //Show the DetailsFragment, replacing the ListFragment at the container
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.screen_container, new DetailsFragment())
                .addToBackStack(null) //To enable back operation
                .commit();
    }
}
