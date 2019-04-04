package com.kaushiknsanji.acviewmodel.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.kaushiknsanji.acviewmodel.home.ListViewModel;
import com.kaushiknsanji.acviewmodel.home.SelectedRepoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Dagger Module for creating and exposing services related to ViewModels.
 *
 * @author Kaushik N Sanji
 */
@Module
public abstract class ViewModelModule {

    /**
     * Method that creates a {@link javax.inject.Provider} Map Entry for the {@link ListViewModel}
     *
     * @param listViewModel Dagger provided instance of {@link ListViewModel}
     * @return Instance of {@link ListViewModel} bound to {@link ViewModel}
     */
    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    abstract ViewModel bindListViewModel(ListViewModel listViewModel);

    /**
     * Method that creates a {@link javax.inject.Provider} Map Entry for the {@link SelectedRepoViewModel}
     *
     * @param selectedRepoViewModel Dagger provided instance of {@link SelectedRepoViewModel}
     * @return Instance of {@link SelectedRepoViewModel} bound to {@link ViewModel}
     */
    @Binds
    @IntoMap
    @ViewModelKey(SelectedRepoViewModel.class)
    abstract ViewModel bindSelectedRepoViewModel(SelectedRepoViewModel selectedRepoViewModel);

}
