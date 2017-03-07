package com.twu.biblioteca;

/**
 * Created by jonnytw on 06/03/2017.
 */
public class CheckoutMovie implements Command {
    private Library library = new Library();
    private UserInput userInput = new UserInput();

    public CheckoutMovie(Library library) {
        this.library = library;
    }

    public CheckoutMovie(Library library, UserInput userInput) {
        this.library = library;
        this.userInput = userInput;
    }

    public void execute() {
        String movieTitle = promptUserForInput("\nPlease enter the TITLE of the movie you wish to checkout. ");
        if (library.containsMovie(movieTitle)) {
            library.removeMovie(movieTitle);
            confirmSuccessfulCheckout("movie", movieTitle);
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
