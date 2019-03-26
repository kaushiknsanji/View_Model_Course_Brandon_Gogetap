package com.kaushiknsanji.acviewmodel.networking;


import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RepoApi {

    private static final String BASE_URL = "https://api.github.com/";

    private static Retrofit sRetrofit;

    private static RepoService sRepoService;

    private RepoApi() {
    }

    private static void initializeRetrofit() {
        sRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    public static RepoService getInstance() {
        if (sRepoService != null) {
            return sRepoService;
        }

        if (sRetrofit == null) {
            initializeRetrofit();
        }

        sRepoService = sRetrofit.create(RepoService.class);
        return sRepoService;
    }

}
