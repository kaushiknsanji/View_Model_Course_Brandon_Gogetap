package com.kaushiknsanji.acviewmodel.base;

import com.kaushiknsanji.acviewmodel.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger Component for exposing services from the Module {@link NetworkModule}
 */
@Singleton
@Component(modules = NetworkModule.class)
public interface ApplicationComponent {
}
