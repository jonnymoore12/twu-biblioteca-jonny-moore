package com.twu.biblioteca;

import java.util.HashMap;

public class BibliotecaApp {

    private Library library = new Library();
    private UserAccount userAccount = new UserAccount();
    private static UserInput userInput = new UserInput();
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
        bibliotecaApp.welcomeMessage();
        bibliotecaApp.login();
        bibliotecaApp.populateMenuItemHashMap();
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
        String choice = getUserMenuSelection();
        executeMenuSelection(choice);
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

    public String getUserMenuSelection() {
        return userInput.getStringInput();
    }

    public void executeMenuSelection(String choice) {

        Command command = menuItemHashMap.get(choice);
        command.execute();
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
}
