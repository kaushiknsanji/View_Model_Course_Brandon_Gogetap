package com.kaushiknsanji.acviewmodel.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Factory class for instantiating {@link ViewModel}s.
 *
 * @author Kaushik N Sanji
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    //Dagger generated Provider Map of ViewModels
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> mViewModelsProviderMap;

    /**
     * Constructor of {@link ViewModelFactory}
     *
     * @param viewModelsProviderMap Dagger injected Provider Map of the form {@code Map<K, Provider<V>>}
     *                              where <K> is a ViewModel Class and <V> is the ViewModel instance.
     */
    @Inject
    ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelsProviderMap) {
        mViewModelsProviderMap = viewModelsProviderMap;
    }

    /**
     * Creates a new instance of the given {@code Class}.
     *
     * @param modelClass a {@code Class} whose instance is requested
     * @return a newly created ViewModel
     */
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            //noinspection unchecked
            return (T) mViewModelsProviderMap.get(modelClass).get();
        } catch (Exception e) {
            throw new RuntimeException("Error creating ViewModel for class: " + modelClass.getSimpleName(), e);
        }
    }
}
