package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by JonnyM on 20/02/2017.
 */
public class UserInput {

    private Scanner in = new Scanner(System.in);

    public int getIntegerInput() {
        return in.nextInt();
    }

    public String getStringInput() {
        return in.nextLine();
    }
}
