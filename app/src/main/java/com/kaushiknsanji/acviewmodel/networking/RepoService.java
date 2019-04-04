package com.kaushiknsanji.acviewmodel.networking;

import com.kaushiknsanji.acviewmodel.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Retrofit API Contract interface for communicating with GitHub services.
 *
 * @author Kaushik N Sanji
 */
public interface RepoService {

    /**
     * API Method for requesting a list of Google's GitHub Repositories.
     *
     * @return A {@link Call} object for the List of {@link Repo}s downloaded.
     */
    @GET("orgs/Google/repos")
    Call<List<Repo>> getRepositories();

    /**
     * API Method for requesting details of particular GitHub Repository.
     *
     * @param repoOwner The Owner/User Name of the GitHub Repository.
     * @param repoName  The Repository Name of the GitHub Repository.
     * @return A {@link Call} object for the {@link Repo} details downloaded.
     */
    @GET("repos/{owner}/{name}")
    Call<Repo> getRepo(@Path("owner") String repoOwner, @Path("name") String repoName);
}
