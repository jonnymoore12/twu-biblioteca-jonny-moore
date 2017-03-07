package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JonnyM on 23/02/2017.
 */
public class UserAccount {

    private List<User> users = new ArrayList(Arrays.asList(new User("Jonny", "jonny@mail.com",
            "5551234", "123-4567", "password")));
    private User currentUser;

    public UserAccount(List<User> users) {
        this.users = users;
    }

    public UserAccount() {
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean verifyLogin(String libraryNumber, String password) {
        for (User user : users) {
            if (libraryNumber.equals(user.getLibraryNumber()) && password.equals(user.getPassword())) {
                logIn(user);
                return true;
            }
        }
        return false;
    }

    public void logIn(User user) {
        this.currentUser = user;
    }
}
