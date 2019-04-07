package com.kaushiknsanji.acviewmodel.networking;

import com.kaushiknsanji.acviewmodel.model.ModelAdapterFactory;
import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Dagger Module for creating and exposing Network related services, tied to the Application Lifecycle.
 *
 * @author Kaushik N Sanji
 */
@Module
public class NetworkModule {

    //Base Url of the GitHub API
    private static final String BASE_URL = "https://api.github.com/";

    /**
     * Method that creates and returns {@link Moshi} instance
     * that uses the {@link ModelAdapterFactory} for JSON de/serialization.
     *
     * @return New or existing instance of {@link Moshi}
     */
    @Provides
    @Singleton
    Moshi getMoshi() {
        return new Moshi.Builder()
                .add(ModelAdapterFactory.create())
                .build();
    }

    /**
     * Method that creates and returns {@link Retrofit} instance
     * for the url {@link #BASE_URL}
     *
     * @param moshi Instance of {@link Moshi} provided by Dagger
     * @return New or existing instance of {@link Retrofit}
     */
    @Provides
    @Singleton
    Retrofit getRetrofit(Moshi moshi) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
    }

    /**
     * Method that creates and returns a {@link RepoService} API instance.
     *
     * @param retrofit Instance of {@link Retrofit} provided by Dagger
     * @return New or existing instance of {@link RepoService} API.
     */
    @Provides
    @Singleton
    RepoService getRepoService(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }

}