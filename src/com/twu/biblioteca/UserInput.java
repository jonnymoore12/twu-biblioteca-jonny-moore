package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by JonnyM on 20/02/2017.
 */
public class UserInput {

    public int getIntegerInput() {
        return new Scanner(System.in).nextInt();
    }

    public String getStringInput() {
        return new Scanner(System.in).nextLine();
    }
}
