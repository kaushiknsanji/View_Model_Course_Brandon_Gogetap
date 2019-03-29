package com.kaushiknsanji.acviewmodel.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.kaushiknsanji.acviewmodel.model.Repo;
import com.kaushiknsanji.acviewmodel.networking.RepoService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedRepoViewModel extends ViewModel {

    //Constant used for logs
    private static final String LOG_TAG = SelectedRepoViewModel.class.getSimpleName();

    //Bundle Key constant to save/restore the state of Repo details
    private static final String BUNDLE_REPO_DETAILS_STR_ARRAY_KEY = "string_array.RepoDetails";

    //To expose the Selected Repo data
    private final MutableLiveData<Repo> mLiveSelectedRepo = new MutableLiveData<>();

    //Instance of Retrofit Service for GitHub API Calls
    private final RepoService mRepoService;

    //Reference to the request for the details of the Repo
    private Call<Repo> mRepoDetailsCall;

    /**
     * Constructor of {@link SelectedRepoViewModel}
     *
     * @param repoService Dagger provided instance of {@link RepoService}
     */
    @Inject
    SelectedRepoViewModel(RepoService repoService) {
        //Save the Retrofit Service
        mRepoService = repoService;
    }

    /**
     * Method that emits a Live Selected {@link Repo} data
     *
     * @return LiveData of the Selected {@link Repo}
     */
    public LiveData<Repo> getLiveSelectedRepo() {
        return mLiveSelectedRepo;
    }

    /**
     * Method that saves/updates the Selected Repo on the LiveData #mLiveSelectedRepo
     *
     * @param repo The Selected {@link Repo} data
     */
    public void setSelectedRepo(Repo repo) {
        mLiveSelectedRepo.setValue(repo);
    }

    /**
     * Method that saves the state of this ViewModel in the Bundle of the Activity/Fragment
     *
     * @param outState Bundle in which to place the saved state
     */
    public void saveToBundle(Bundle outState) {
        if (mLiveSelectedRepo.getValue() != null) {
            //When we have the Selected Repo data

            //Get the Selected Repo
            Repo repo = mLiveSelectedRepo.getValue();
            //Save the Selected Repo's Owner Name and Repo Name information for restoration at a later time
            outState.putStringArray(
                    BUNDLE_REPO_DETAILS_STR_ARRAY_KEY,
                    new String[]{repo.getOwner().getLogin(), repo.getName()}
            );
        }
    }

    /**
     * Method that restores the state of this ViewModel from the Bundle of the Activity/Fragment
     * having the required saved information if any.
     *
     * @param savedInstanceState Bundle containing the saved state if any
     */
    public void restoreFromBundle(Bundle savedInstanceState) {
        if (mLiveSelectedRepo.getValue() == null
                && savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_REPO_DETAILS_STR_ARRAY_KEY)) {
            //Restoring Selected Repo details when NOT previously set and is available in the Bundle for restoration
            loadRepo(savedInstanceState.getStringArray(BUNDLE_REPO_DETAILS_STR_ARRAY_KEY));
        }
    }

    /**
     * Method that restores the state of this ViewModel by making a Network request call to get
     * the details of Selected Repo using the Owner Name and Repo Name information passed in {@code repoDetailsArray}
     *
     * @param repoDetailsArray String array of the Selected Repo's Owner Name and Repo Name information
     *                         required for retrieving the complete Selected {@link Repo} information
     */
    private void loadRepo(String[] repoDetailsArray) {
        //Make a request to get the Repo details
        mRepoDetailsCall = mRepoService.getRepo(repoDetailsArray[0], repoDetailsArray[1]);
        //Enqueue the above request
        mRepoDetailsCall.enqueue(new Callback<Repo>() {
            /**
             * Invoked for a received HTTP response.
             * <p>
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call {@link Response#isSuccessful()} to determine if the response indicates success.
             */
            @Override
            public void onResponse(@NonNull Call<Repo> call, @NonNull Response<Repo> response) {
                if (response.isSuccessful()) {
                    //When the response is successful

                    //Save the Repo details
                    setSelectedRepo(response.body());
                    //Clearing reference to the call
                    mRepoDetailsCall = null;
                }
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            @Override
            public void onFailure(@NonNull Call<Repo> call, @NonNull Throwable t) {
                //Log the failure message
                Log.e(LOG_TAG, "onFailure: Error Loading Repo", t);
                //Clearing reference to the call
                mRepoDetailsCall = null;
            }
        });
    }

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     * <p>
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    @Override
    protected void onCleared() {
        //Canceling any pending request if still in progress
        if (mRepoDetailsCall != null) {
            mRepoDetailsCall.cancel();
            mRepoDetailsCall = null;
        }
    }
}
