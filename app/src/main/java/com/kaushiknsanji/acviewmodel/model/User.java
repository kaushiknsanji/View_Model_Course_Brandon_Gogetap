package com.kaushiknsanji.acviewmodel.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Remote Model for storing the User/Owner information of a GitHub {@link Repo}.
 *
 * @author Kaushik N Sanji
 */
@AutoValue
public abstract class User {

    /**
     * Method that returns the {@link JsonAdapter} for use with {@link Moshi}
     * de/serialization of JSON.
     *
     * @param moshi Instance of {@link Moshi}
     * @return Instance of {@link JsonAdapter}
     */
    public static JsonAdapter<User> jsonAdapter(Moshi moshi) {
        return new AutoValue_User.MoshiJsonAdapter(moshi);
    }

    /**
     * Getter Method for the Login/User Name of the Repository
     *
     * @return Login/User Name of the Repository
     */
    public abstract String getLogin();

}