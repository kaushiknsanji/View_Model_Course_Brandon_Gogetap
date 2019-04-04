package com.kaushiknsanji.acviewmodel.base;

import com.kaushiknsanji.acviewmodel.details.DetailsFragment;
import com.kaushiknsanji.acviewmodel.home.ListFragment;
import com.kaushiknsanji.acviewmodel.networking.NetworkModule;
import com.kaushiknsanji.acviewmodel.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger Component for exposing services from the Module {@link NetworkModule}.
 *
 * @author Kaushik N Sanji
 */
@Singleton
@Component(modules = {NetworkModule.class, ViewModelModule.class})
public interface ApplicationComponent {

    /**
     * Method to inject services into the client {@link ListFragment}
     *
     * @param listFragment Instance of {@link ListFragment}
     */
    void inject(ListFragment listFragment);

    /**
     * Method to inject services into the client {@link DetailsFragment}
     *
     * @param detailsFragment Instance of {@link DetailsFragment}
     */
    void inject(DetailsFragment detailsFragment);

}