package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by jonnytw on 06/03/2017.
 */
public class CheckoutBook implements Command {

    private Library library = new Library();
    private UserInput userInput = new UserInput();

    public CheckoutBook(Library library) {
        this.library = library;
    }

    public CheckoutBook(Library library, UserInput userInput) {
        this.library = library;
        this.userInput = userInput;
    }

    public void execute() {
        String bookTitle = promptUserForInput("\nPlease enter the TITLE of the book you wish to checkout. ");
        if (library.containsBook(bookTitle)) {
            library.removeBook(bookTitle);
            confirmSuccessfulCheckout("book", bookTitle);
        } else {
            invalidSelection();
            execute();
        }
    }

    private String promptUserForInput(String prompt) {
        System.out.println(prompt);
        return userInput.getStringInput();
    }

    private void confirmSuccessfulCheckout(String type, String title) {
        System.out.println("\n\nYou have successfully checked out '" + title + "'. Thank you. Enjoy your " + type + "!");
    }

    private void invalidSelection() {
        System.out.println("\nInvalid selection. Please select a valid option!");
    }
}
