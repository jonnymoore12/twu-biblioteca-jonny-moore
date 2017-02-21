package com.twu.biblioteca;

public class BibliotecaApp {

    private static Library library = new Library();
    private static UserInput userInput = new UserInput();

    public BibliotecaApp(Library library, UserInput userInput) {
        this.library = library;
        this.userInput = userInput;
    }

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp(new Library(), new UserInput());
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

    public void displayMenuOptions() {
        System.out.println("");
        System.out.println("MAIN MENU");
        System.out.println("---------");
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
            case 2:
                checkoutBook();
                break;
            case 4:
                quit();
                break;
            default:
                invalidSelection();;
                getUserSelection();
                break;
        }
        topMenu();
    }

    public void listBooks() {

        printBookDetailsColumns();

        for (Book book : library.getBooks()) {
            printBookInfo(book);
        }
    }

    public void invalidSelection() {
        System.out.println("\n\nInvalid selection. Please select a valid option!");
    }

    public void checkoutBook() {
        listBooks();
        String bookTitle = checkoutBookPrompt();

        if (library.containsBook(bookTitle)) {
            library.removeBook(bookTitle);
            confirmSuccessfulCheckout(bookTitle);
        } else {
            invalidSelection();
            checkoutBook();
        }
    }

    private String checkoutBookPrompt() {
        System.out.println("\nPlease select from the available books by entering the TITLE of the book you wish to checkout.");
        return userInput.getStringInput();
    }

    private void confirmSuccessfulCheckout(String bookTitle) {
        System.out.println("\nYou have successfully checked out '" + bookTitle + "'. Thank you! Enjoy your book");
    }

    private void printBookDetailsColumns() {
        spaceContentAcrossRow("\nTitle", " Author", "Year");
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

    public String returnsFred() {
        return "Fred";
    }
}
