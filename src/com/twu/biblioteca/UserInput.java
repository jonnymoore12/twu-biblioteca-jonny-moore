package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by JonnyM on 20/02/2017.
 */
public class UserInput {

    private Scanner scanner = new Scanner(System.in);

    public String getStringInput() {
        return scanner.nextLine();
    }
}
