package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by JonnyM on 20/02/2017.
 */
public class UserInput {

    private int userInput;
    private String stringInput;
    private Scanner in = new Scanner(System.in);

    public int getIntegerInput() {
        userInput = in.nextInt();
        return userInput;
    }

    public String getStringInput() {
        stringInput = in.next();
        return stringInput;
    }
}
