package com.kaushiknsanji.acviewmodel.networking;

import com.kaushiknsanji.acviewmodel.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RepoService {

    @GET("orgs/Google/repos")
    Call<List<Repo>> getRepositories();

}
