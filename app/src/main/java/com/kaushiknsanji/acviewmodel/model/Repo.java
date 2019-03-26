package com.kaushiknsanji.acviewmodel.model;

import com.squareup.moshi.Json;


public class Repo {

    private final long mId;

    private final String mName;

    private final String mDescription;

    private final User mOwner;

    @Json(name = "stargazers_count")
    private final long mStars;

    @Json(name = "forks_count")
    private final long mForks;

    public Repo(long id, String name, String description, User owner, long stars, long forks) {
        mId = id;
        mName = name;
        mDescription = description;
        mOwner = owner;
        mStars = stars;
        mForks = forks;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public User getOwner() {
        return mOwner;
    }

    public long getStars() {
        return mStars;
    }

    public long getForks() {
        return mForks;
    }
}
