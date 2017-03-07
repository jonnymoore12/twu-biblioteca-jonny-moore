package com.twu.biblioteca;

/**
 * Created by jonnytw on 06/03/2017.
 */
public class ReturnBook implements Command {

    private Library library = new Library();
    private UserInput userInput = new UserInput();

    public ReturnBook(Library library, UserInput userInput) {
        this.library = library;
        this.userInput = userInput;
    }

    public ReturnBook(Library library) {
        this.library = library;
    }

    public void execute() {
        String bookTitle = promptUserForInput("\nPlease enter the title of the book you wish to return.");
        if (library.bookWaitingToBeReturned(bookTitle)) {
            library.returnBook(bookTitle);
            System.out.println("\nThank you for returning '" + bookTitle + "'.");
        } else if (library.containsBook(bookTitle)) {
            System.out.println("\nThis book is already in the Biblioteca. You must have the wrong library!");
        } else {
            System.out.println("\nThat is not a valid book to return. Please try again.");
            execute();
        }
    }

    private String promptUserForInput(String prompt) {
        System.out.println(prompt);
        return userInput.getStringInput();
    }

}
