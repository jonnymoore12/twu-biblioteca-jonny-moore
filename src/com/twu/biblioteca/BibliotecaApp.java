package com.twu.biblioteca;

import java.util.HashMap;

public class BibliotecaApp {

    private Library library = new Library();
    private UserAccount userAccount = new UserAccount();
    private static UserInput userInput = new UserInput();
    private String userChoice = "";
    private enum MenuItem { LIST_BOOKS, LIST_MOVIES};
    private HashMap<String, Command> menuItemHashMap = new HashMap<String, Command>();

    public BibliotecaApp(Library library, UserAccount userAccount, UserInput userInput) {
        this.library = library;
        this.userAccount = userAccount;
        this.userInput = userInput;
        populateMenuItemHashMap();
    }

    public BibliotecaApp(Library library, UserInput userInput) {
        this.library = library;
        this.userInput = userInput;
    }

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(new Library(), new UserAccount(), new UserInput());
//        bibliotecaApp.populateMenuItemHashMap();
        bibliotecaApp.welcomeMessage();
        bibliotecaApp.login();
        bibliotecaApp.topMenu();
    }

    public void welcomeMessage() {
        System.out.println("==========================================================");
        System.out.println("==================Welcome to Biblioteca!==================");
        System.out.println("===============The Bangalore Public Library===============");
        System.out.println("==========================================================");
    }

    public void login() {
        String attemptedLibraryNumber = promptUserForInput("Please enter your login credentials to login." +
                                                                                                " Library number:");
        String attemptedPassword = promptUserForInput("Please enter your password:");

        if (userAccount.verifyLogin(attemptedLibraryNumber, attemptedPassword)) {
            System.out.println("Login successful. Welcome back to Biblioteca!");
        } else {
            System.out.println("I'm afraid something went wrong. Login unsuccessful.");
            login();
        }
    }

    public void topMenu() {
        displayMenuOptions();
        getUserSelection();
        executeMenuSelection();
        // get rid of this later:
        topMenu();
    }

    public void displayMenuOptions() {
        System.out.println("\n\nMAIN MENU");
        System.out.println("---------");
        System.out.println("Please select from the following options (enter number):");
        System.out.println("1. List Books");
        System.out.println("2. List Movies");
        System.out.println("3. Checkout Book");
        System.out.println("4. Checkout Movie");
        System.out.println("5. Return Book");
        System.out.println("6. Return Movie");
        System.out.println("7. Display User Info");
        System.out.println("8. Quit");
    }

    public void getUserSelection() {
        userChoice = userInput.getStringInput();
    }

    public void executeMenuSelection() {
        Command command = menuItemHashMap.get(userChoice);
        command.execute();
    }

//    private void getUserSelection() {
//        switch (userInput.getStringInput()) {
//            case "1":
//                ListBooks listBooks = new ListBooks(library);
//                listBooks.execute();
//                break;
//            case "2":
//                listMovies();
//                break;
//            case "3":
//                checkoutBook();
//                break;
//            case "4":
//                checkoutMovie();
//                break;
//            case "5":
//                returnBook();
//                break;
//            case "6":
//                returnMovie();
//                break;
//            case "7":
//                showUserInfo();
//                break;
//            case "8":
//                quit();
//                System.exit(0);
//            default:
//                invalidSelection();;
//                getUserSelection();
//                break;
//        }
//        topMenu();
//    }

    public void checkoutBook() {
        String bookTitle = promptUserForInput("\nPlease enter the TITLE of the book you wish to checkout. " +
                                                                                 "(For main menu, enter: main menu).");
        if (library.containsBook(bookTitle)) {
            library.removeBook(bookTitle);
            confirmSuccessfulCheckout("book", bookTitle);
        } else if (bookTitle.contains("main menu")) {
            topMenu();
        } else {
            invalidSelection();
            checkoutBook();
        }
    }

    public void checkoutMovie() {
        String movieName = promptUserForInput("\nPlease enter the NAME of the movie you wish to checkout. " +
                                                                                "(For main menu, enter: main menu).");
        if (library.containsMovie(movieName)) {
            library.removeMovie(movieName);
            confirmSuccessfulCheckout("movie", movieName);
        } else if (movieName.contains("main menu")) {
            topMenu();
        } else {
            invalidSelection();
            checkoutMovie();
        }
    }

    public void returnBook() {
        String bookTitle = promptUserForInput("\nPlease enter the title of the book you wish to return. " +
                                                                            "For main menu, enter: main menu).");
        if (library.bookWaitingToBeReturned(bookTitle)) {
            library.returnBook(bookTitle);
            System.out.println("\nThank you for returning '" + bookTitle + "'.");
        } else if (library.containsBook(bookTitle)) {
            System.out.println("\nThis book is already in the Biblioteca. You must have the wrong library!");
        } else if (bookTitle.contains("main menu")) {
            topMenu();
        } else {
            System.out.println("\nThat is not a valid book to return. Please try again.");
            returnBook();
        }
    }

    public void returnMovie() {
        String movieName = promptUserForInput("\nPlease enter the name of the book you wish to return. " +
                "For main menu, enter: main menu).");

        if (library.movieWaitingToBeReturned(movieName)) {
            library.returnMovie(movieName);
            System.out.println("\nThank you for returning '" + movieName + "'.");
        } else if (library.containsMovie(movieName)) {
            System.out.println("\nThis movie is already in the Biblioteca. You must have the wrong library!");
        } else if (movieName.contains("main menu")) {
            topMenu();
        } else {
            System.out.println("That is not a valid movie to return! Please try again.");
            returnMovie();
        }
    }

    public String getUserChoice() {
        return userChoice;
    }

    private void populateMenuItemHashMap() {
        menuItemHashMap.put("1", new ListBooks(library));
        menuItemHashMap.put("2", new ListMovies(library));
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

    public void showUserInfo() {
        userAccount.showCurrentUserInfo();
    }

    public void quit() {
        System.out.println("Thanks for using Biblioteca. See you next time.");
    }

}
