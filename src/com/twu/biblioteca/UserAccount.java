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
    private boolean hasActiveUser = false;

    public UserAccount() {
    }

    public boolean getHasActiveUser() {
        return hasActiveUser;
    }

    public void setHasActiveUser() {
        hasActiveUser = true;
    }

    public String loginPrompt() {
        return "Please enter your library number to login:";
    }

    public boolean verifyLogin(String libraryNumber, String password) {
        for (User user : users) {
            if (libraryNumber == user.getLibraryNumber() && password == user.getPassword()) {
                setHasActiveUser();
                return true;
            }
        }
        return false;
    }
}
