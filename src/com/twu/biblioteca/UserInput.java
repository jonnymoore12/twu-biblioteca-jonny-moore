package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by JonnyM on 20/02/2017.
 */
public class UserInput {

    private String userInput;

    public String getUserInput() {
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        return userInput;
    }
}
