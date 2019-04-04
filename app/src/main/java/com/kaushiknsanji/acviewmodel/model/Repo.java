package com.kaushiknsanji.acviewmodel.model;

import com.squareup.moshi.Json;

/**
 * Remote Model for storing the details of a GitHub Repository.
 *
 * @author Kaushik N Sanji
 */
public class Repo {

    //JSON field for Repository ID
    private final long id;

    //JSON field for Repository Name
    private final String name;

    //JSON field for Repository Description
    private final String description;

    //JSON object for User information
    private final User owner;

    //JSON field for Repository Stars count
    @Json(name = "stargazers_count")
    private final long stars;

    //JSON field for Repository Forks count
    @Json(name = "forks_count")
    private final long forks;

    /**
     * Constructor of {@link Repo}
     *
     * @param id          A {@link Long} value of Repository ID
     * @param name        Repository Name
     * @param description Repository Description
     * @param owner       A {@link User} information of the Repository
     * @param stars       A {@link Long} value of the number of Stars on the Repository
     * @param forks       A {@link Long} value of the number of Forks on the Repository
     */
    public Repo(long id, String name, String description, User owner, long stars, long forks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.stars = stars;
        this.forks = forks;
    }

    /**
     * Getter Method for the Repository ID
     *
     * @return A {@link Long} value of Repository ID
     */
    public long getId() {
        return id;
    }

    /**
     * Getter Method for the Repository Name
     *
     * @return Name of the Repository
     */
    public String getName() {
        return name;
    }

    /**
     * Getter Method for the Repository Description
     *
     * @return Description of the Repository
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter Method for the {@link User} information of the Repository
     *
     * @return A {@link User} information of the Repository
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Getter Method for the number of Stars on the Repository
     *
     * @return A {@link Long} value of the number of Stars on the Repository
     */
    public long getStars() {
        return stars;
    }

    /**
     * Getter Method for the number of Forks on the Repository
     *
     * @return A {@link Long} value of the number of Forks on the Repository
     */
    public long getForks() {
        return forks;
    }
}
