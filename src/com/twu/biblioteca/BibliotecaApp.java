package com.twu.biblioteca;

public class BibliotecaApp {

    private static Library library = new Library();
    private static UserInput userInput = new UserInput();

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.run();
        bibliotecaApp.topMenu();
    }

    public void run() {
        System.out.println("Welcome to Biblioteca!");
    }

    public void topMenu() {
        displayMenuOptions();
        getUserSelection();
    }

    public void listBooks() {

        printBookDetailsColumns();

        for (Book book : library.getBooks()) {
            printBookInfo(book);
        }
    }

    public void displayMenuOptions() {
        System.out.println("");
        System.out.println("Please select from the following options (enter number):");
        System.out.println("1. List Books");
        System.out.println("2. Checkout Book");
        System.out.println("3. Return Book");
        System.out.println("4. Quit");
    }

    private void getUserSelection() {
        switch (userInput.getIntegerInput()) {
            case 1:
                listBooks();
                break;
            case 4:
                quit();
                System.exit(0);
                break;
            default:
                invalidSelection();;
                getUserSelection();
                break;
        }
    }

    public void invalidSelection() {
        System.out.println("Invalid selection. Please select a valid option!");
    }

    public void checkoutBook() {
        System.out.println("Please select from the following books by entering the TITLE of the book you wish to checkout.");
        listBooks();
//        userInput.getIntegerInput();
        // Iterate over the book titles
    }

//    private void userInputsBookTitle() {
//        String input = userInput.getUserInput();
//
//    }

    private void printBookDetailsColumns() {
        spaceContentAcrossRow("Title", "Author", "Year");
        spaceContentAcrossRow("-----", "------", "----");
    }

    private void printBookInfo(Book book) {
        spaceContentAcrossRow(book.getTitle(), book.getAuthor(), book.getYear());
    }

    private void spaceContentAcrossRow(String column1, String column2, String column3) {
        System.out.printf("%-20s %-20s %10s %n", column1, column2, column3);
    }

    public void quit() {
        System.out.println("Exiting Biblioteca. See you next time.");
    }

    public void successfulCheckout() {
        System.out.println("You have successfully checked out 'Brave New World'. Enjoy your book");
    }
}
