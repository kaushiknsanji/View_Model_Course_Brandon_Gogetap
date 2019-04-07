package com.kaushiknsanji.acviewmodel.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Remote Model for storing the details of a GitHub Repository.
 *
 * @author Kaushik N Sanji
 */
@AutoValue
public abstract class Repo {

    /**
     * Method that returns the {@link JsonAdapter} for use with {@link Moshi}
     * de/serialization of JSON.
     *
     * @param moshi Instance of {@link Moshi}
     * @return Instance of {@link JsonAdapter}
     */
    public static JsonAdapter<Repo> jsonAdapter(Moshi moshi) {
        return new AutoValue_Repo.MoshiJsonAdapter(moshi);
    }

    /**
     * Getter Method for the Repository ID
     *
     * @return A {@link Long} value of Repository ID
     */
    public abstract long getId();

    /**
     * Getter Method for the Repository Name
     *
     * @return Name of the Repository
     */
    public abstract String getName();

    /**
     * Getter Method for the Repository Description
     *
     * @return Description of the Repository
     */
    @Nullable
    public abstract String getDescription();

    /**
     * Getter Method for the {@link User} information of the Repository
     *
     * @return A {@link User} information of the Repository
     */
    public abstract User getOwner();

    /**
     * Getter Method for the number of Stars on the Repository
     *
     * @return A {@link Long} value of the number of Stars on the Repository
     */
    @Json(name = "stargazers_count")
    public abstract long getStars();

    /**
     * Getter Method for the number of Forks on the Repository
     *
     * @return A {@link Long} value of the number of Forks on the Repository
     */
    @Json(name = "forks_count")
    public abstract long getForks();
}
