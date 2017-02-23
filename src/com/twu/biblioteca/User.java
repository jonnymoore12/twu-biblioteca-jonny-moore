package com.twu.biblioteca;

/**
 * Created by JonnyM on 23/02/2017.
 */
public class User {

    private String username;
    private String libraryNumber;
    private String password;

    public User(String username, String libraryNumber, String password) {
        this.username = username;
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }
}
