package com.twu.biblioteca;

import java.util.HashMap;

public class BibliotecaApp {

    private Library library = new Library();
    private UserAccount userAccount = new UserAccount();
    private static UserInput userInput = new UserInput();
    private String userChoice = "";
//    private enum MenuItem { LIST_BOOKS ("1"), LIST_MOVIES ("2");
//                            String selection;
//                            MenuItem(String s) {
//                                selection = s;
//                            }
//                            };
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
        populateMenuItemHashMap();
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

    private void populateMenuItemHashMap() {
        menuItemHashMap.put("1", new ListBooks(library));
        menuItemHashMap.put("2", new ListMovies(library));
        menuItemHashMap.put("3", new CheckoutBook(library));
        menuItemHashMap.put("4", new CheckoutMovie(library));
        menuItemHashMap.put("5", new ReturnBook(library));
        menuItemHashMap.put("6", new ReturnMovie(library));
        menuItemHashMap.put("7", new DisplayUserInfo(userAccount.getCurrentUser()));
        menuItemHashMap.put("8", new Quit());
    }

    private String promptUserForInput(String prompt) {
        System.out.println(prompt);
        return userInput.getStringInput();
    }

    public void quit() {
        System.out.println("Thanks for using Biblioteca. See you next time.");
    }

}
