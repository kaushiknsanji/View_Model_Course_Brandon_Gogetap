package com.kaushiknsanji.acviewmodel.model;

/**
 * Remote Model for storing the User/Owner information of a GitHub {@link Repo}.
 *
 * @author Kaushik N Sanji
 */
public class User {

    //JSON field for the Login/User Name of the Repository
    private final String login;

    /**
     * Constructor of {@link User}
     *
     * @param login Login/User Name of the Repository
     */
    public User(String login) {
        this.login = login;
    }

    /**
     * Getter Method for the Login/User Name of the Repository
     *
     * @return Login/User Name of the Repository
     */
    public String getLogin() {
        return login;
    }
}
