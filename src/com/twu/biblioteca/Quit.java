package com.twu.biblioteca;

/**
 * Created by jonnytw on 07/03/2017.
 */
public class Quit implements Command {

    public void execute() {
        System.out.println("Thanks for using Biblioteca. See you next time.");
        System.exit(0);
    }
}
