package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by JonnyM on 20/02/2017.
 */
public class UserInput {

    private int userInput;

    public int getUserInput() {
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            userInput = in.nextInt();
        }
        return userInput;
    }
}
