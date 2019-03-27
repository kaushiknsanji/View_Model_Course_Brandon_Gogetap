package com.kaushiknsanji.acviewmodel.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kaushiknsanji.acviewmodel.model.Repo;

public class SelectedRepoViewModel extends ViewModel {

    //To expose the Selected Repo data
    private final MutableLiveData<Repo> mLiveSelectedRepo = new MutableLiveData<>();

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
}
