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
        System.out.println("\n\nMAIN MENU");
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
                listAllBooks();
                break;
            case 2:
                checkoutBook();
                break;
            case 3:
                returnBook();
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

    public void listAllBooks() {

        System.out.println("\n\nCheck out the complete book listing at Biblioteca:");
        printBookDetailsColumns();

        for (Book book : library.getBooks()) {
            printBookInfo(book);
        }
    }

    public void onlyListAvailableBooks() {

        System.out.println("\n\nThese are the currently available books at Biblioteca:");
        printBookDetailsColumns();

        for (Book book : library.getBooks()) {
            if (book.isAvailable()) {
                printBookInfo(book);
            }
        }
    }

    public void invalidSelection() {
        System.out.println("\n\n\nInvalid selection. Please select a valid option!");
    }

    public void checkoutBook() {
        onlyListAvailableBooks();
        String bookTitle = promptUserForInput("\n\nPlease select from the available books by entering " +
                "the TITLE of the book you wish to checkout. (For main menu, enter: main menu)");
        if (library.containsBook(bookTitle)) {
            library.removeBook(bookTitle);
            confirmSuccessfulCheckout(bookTitle);
        } else if (bookTitle.contains("main menu")) {
            topMenu();
        } else {
            invalidSelection();
            checkoutBook();
        }
    }

    public void returnBook() {
        String bookTitle = promptUserForInput("Please enter the title of the book you wish to return:");
        if (library.bookWaitingToBeReturned(bookTitle)) {
            library.returnBook(bookTitle);
            System.out.println("\nThank you for returning the book.");
        } else if (library.containsBook(bookTitle)) {
            System.out.println("\nThis book is already in the Biblioteca. You must have the wrong library!");
        } else {
            System.out.println("\nThat is not a valid book to return. Please try again.");
            returnBook();
        }
    }

    private String promptUserForInput(String prompt) {
        System.out.println(prompt);
        return userInput.getStringInput();
    }

    private void confirmSuccessfulCheckout(String bookTitle) {
        System.out.println("\n\nYou have successfully checked out '" + bookTitle + "'. Thank you! Enjoy your book.");
    }

    private void printBookDetailsColumns() {
        spaceContentAcrossRow("Title", " Author", "Year");
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

}
