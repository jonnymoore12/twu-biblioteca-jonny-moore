package com.twu.biblioteca;

/**
 * Created by jonnytw on 07/03/2017.
 */
public class DisplayUserInfo implements Command {

    private User currentUser;

    public DisplayUserInfo(User user) {
        this.currentUser = user;
    }

    public void execute() {
        System.out.println("My User Information:");
        System.out.println("Username: " + currentUser.getUsername());
        System.out.println("email address: " + currentUser.getEmail());
        System.out.println("Phone number: " + currentUser.getPhoneNumber());
    }
}
