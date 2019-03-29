package com.kaushiknsanji.acviewmodel.details;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaushiknsanji.acviewmodel.R;
import com.kaushiknsanji.acviewmodel.base.BaseFragment;
import com.kaushiknsanji.acviewmodel.home.SelectedRepoViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class DetailsFragment extends BaseFragment {

    @BindView(R.id.tv_repo_name)
    TextView mTextViewRepoName;

    @BindView(R.id.tv_repo_description)
    TextView mTextViewRepoDescription;

    @BindView(R.id.tv_forks)
    TextView mTextViewForks;

    @BindView(R.id.tv_stars)
    TextView mTextViewStars;

    private Unbinder mUnbinder;

    private SelectedRepoViewModel mSelectedRepoViewModel;

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
        View rootView = inflater.inflate(R.layout.screen_details, container, false);
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
        //Get the SelectedRepoViewModel Instance
        mSelectedRepoViewModel = ViewModelProviders.of(requireActivity()).get(SelectedRepoViewModel.class);
        //Restore the SelectedRepoViewModel state from the Bundle
        mSelectedRepoViewModel.restoreFromBundle(savedInstanceState);
        //Display the Selected Repo details
        displayRepo();
    }

    /**
     * Called to ask the fragment to save its current dynamic state, so it
     * can later be reconstructed in a new instance of its process is
     * restarted.  If a new instance of the fragment later needs to be
     * created, the data you place in the Bundle here will be available
     * in the Bundle given to {@link #onCreate(Bundle)},
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}, and
     * {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>This corresponds to {@link android.support.v4.app.FragmentActivity#onSaveInstanceState(Bundle)
     * Activity.onSaveInstanceState(Bundle)} and most of the discussion there
     * applies here as well.  Note however: <em>this method may be called
     * at any time before {@link #onDestroy()}</em>.  There are many situations
     * where a fragment may be mostly torn down (such as when placed on the
     * back stack with no UI showing), but its state will not be saved until
     * its owning activity actually needs to save its state.
     *
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the SelectedRepoViewModel state to Bundle
        mSelectedRepoViewModel.saveToBundle(outState);
    }

    /**
     * Method that binds the Selected {@link com.kaushiknsanji.acviewmodel.model.Repo} data
     * to the Views
     */
    private void displayRepo() {
        //Register for loading Selected Repo
        mSelectedRepoViewModel.getLiveSelectedRepo().observe(this, repo -> {
            if (repo != null) {
                //Bind the Selected Repo data to the views when available
                mTextViewRepoName.setText(repo.getName());
                mTextViewRepoDescription.setText(repo.getDescription());
                mTextViewStars.setText(String.valueOf(repo.getStars()));
                mTextViewForks.setText(String.valueOf(repo.getForks()));
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
