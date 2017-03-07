package com.twu.biblioteca;

/**
 * Created by jonnytw on 06/03/2017.
 */
public class ReturnMovie implements Command {

    private Library library = new Library();
    private UserInput userInput = new UserInput();

    public ReturnMovie(Library library, UserInput userInput) {
        this.library = library;
        this.userInput = userInput;
    }

    public ReturnMovie(Library library) {
        this.library = library;
    }

    public void execute() {
        String movieTitle = promptUserForInput("\nPlease enter the title of the movie you wish to return");
        if (library.movieWaitingToBeReturned(movieTitle)) {
            library.returnMovie(movieTitle);
            System.out.println("\nThank you for returning '" + movieTitle + "'.");
        } else if (library.containsMovie(movieTitle)) {
            System.out.println("\nThis movie is already in the Biblioteca. You must have the wrong library!");
        } else {
            System.out.println("\nThat is not a valid movie to return. Please try again.");
            execute();
        }
    }

    private String promptUserForInput(String prompt) {
        System.out.println(prompt);
        return userInput.getStringInput();
    }
}
