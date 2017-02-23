package com.twu.biblioteca;

/**
 * Created by JonnyM on 23/02/2017.
 */
public class User {

    private String username;
    private String email;
    private String phoneNumber;
    private String libraryNumber;
    private String password;

    public User(String username, String email, String phoneNumber, String libraryNumber, String password) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }
}
