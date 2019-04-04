package com.kaushiknsanji.acviewmodel.home;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.kaushiknsanji.acviewmodel.model.Repo;
import com.kaushiknsanji.acviewmodel.networking.RepoService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Custom {@link ViewModel} to fetch and provide a list of Repositories downloaded,
 * to the {@link ListFragment} observers.
 *
 * @author Kaushik N Sanji
 */
public class ListViewModel extends ViewModel {

    //Constant used for logs
    private static final String LOG_TAG = ListViewModel.class.getSimpleName();

    //To expose the list of Repos
    private final MutableLiveData<List<Repo>> mLiveRepos = new MutableLiveData<>();
    //To expose the status of error encountered while loading the Repos data
    private final MutableLiveData<Boolean> mLiveRepoLoadError = new MutableLiveData<>();
    //To expose the loading status of Repos data
    private final MutableLiveData<Boolean> mLiveLoading = new MutableLiveData<>();
    //Instance of Retrofit Service for GitHub API Calls
    private final RepoService mRepoService;
    //Reference to the request for the list of Repositories
    private Call<List<Repo>> mRepoCall;

    /**
     * Constructor of the {@link ListViewModel}
     *
     * @param repoService Dagger provided instance of {@link RepoService}
     */
    @Inject
    ListViewModel(RepoService repoService) {
        //Save the Retrofit Service
        mRepoService = repoService;
        //Load the list of Repositories
        fetchRepos();
    }

    /**
     * Method that emits a Live List of Repositories to the registered Lifecycle Observers
     *
     * @return LiveData of the List of Repositories loaded
     */
    LiveData<List<Repo>> getRepos() {
        return mLiveRepos;
    }

    /**
     * Method that emits the Live status of error encountered while loading the Repositories
     *
     * @return LiveData of Boolean representing the status of error encountered while
     * loading the Repositories
     */
    LiveData<Boolean> getRepoLoadError() {
        return mLiveRepoLoadError;
    }

    /**
     * Method that emits the Live status of loading the Repositories
     *
     * @return LiveData of Boolean representing the status of loading the Repositories
     */
    LiveData<Boolean> getLoading() {
        return mLiveLoading;
    }

    /**
     * Method that loads the Repositories using the GitHub Api
     */
    private void fetchRepos() {
        //Start loading repos, set the loading status to True
        mLiveLoading.setValue(true);

        //Make a request to get the list of Repositories
        mRepoCall = mRepoService.getRepositories();
        //Enqueue the above request
        mRepoCall.enqueue(new Callback<List<Repo>>() {
            /**
             * Invoked for a received HTTP response.
             * <p>
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call {@link Response#isSuccessful()} to determine if the response indicates success.
             */
            @Override
            public void onResponse(@NonNull Call<List<Repo>> call, @NonNull Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    //When the response is successful

                    //Set the error status to False
                    mLiveRepoLoadError.setValue(false);
                    //Set the list of Repositories
                    mLiveRepos.setValue(response.body());

                } else {
                    //When the response is not successful

                    //Log an error message
                    Log.e(LOG_TAG, "onResponse: Failed with code: " + response.code() + "\n" + response.toString());
                    //Set the error status to True
                    mLiveRepoLoadError.setValue(true);
                }

                //Set the loading status as completed
                mLiveLoading.setValue(false);
                //Clearing reference to the call
                mRepoCall = null;
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            @Override
            public void onFailure(@NonNull Call<List<Repo>> call, @NonNull Throwable t) {
                //Log the failure message
                Log.e(LOG_TAG, "onFailure: Error Loading repos", t);
                //Set the error status to True
                mLiveRepoLoadError.setValue(true);
                //Set the loading status as completed
                mLiveLoading.setValue(false);
                //Clearing reference to the call
                mRepoCall = null;
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
        if (mRepoCall != null) {
            mRepoCall.cancel();
            mRepoCall = null;
        }
    }
}
