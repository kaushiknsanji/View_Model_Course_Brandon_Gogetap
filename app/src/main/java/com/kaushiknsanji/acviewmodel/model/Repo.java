package com.kaushiknsanji.acviewmodel.model;

import com.squareup.moshi.Json;


public class Repo {

    private final long id;

    private final String name;

    private final String description;

    private final User owner;

    @Json(name = "stargazers_count")
    private final long stars;

    @Json(name = "forks_count")
    private final long forks;

    public Repo(long id, String name, String description, User owner, long stars, long forks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.stars = stars;
        this.forks = forks;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getOwner() {
        return owner;
    }

    public long getStars() {
        return stars;
    }

    public long getForks() {
        return forks;
    }
}
