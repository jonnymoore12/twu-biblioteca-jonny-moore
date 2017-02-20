package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    private static Library library = new Library();
    private static UserInput userInput = new UserInput();

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.run();
        BibliotecaApp.displayMenu();
    }

    public void run() {
        System.out.println("Welcome to Biblioteca!");
    }

    public static void displayMenu() {
        System.out.println("");
        System.out.println("Please select from the following options (enter number):");
        System.out.println("1. List Books");
        System.out.println("2. Checkout Book");
        System.out.println("3. Return Book");
        System.out.println("4. Quit");

        switch (userInput.getUserInput()) {
            case 1:
                listBooks();
            default:
                System.out.println("TBC");
                break;
        }
    }

    public static void listBooks() {

        printBookDetailsColumns();

        for (Book book : library.getBooks()) {
            printBookInfo(book);
        }
    }

    private static void printBookDetailsColumns() {
        spaceContentAcrossRow("Title", "Author", "Year");
        spaceContentAcrossRow("-----", "------", "----");
    }

    private static void printBookInfo(Book book) {
        spaceContentAcrossRow(book.getTitle(), book.getAuthor(), book.getYear());
    }

    private static void spaceContentAcrossRow(String column1, String column2, String column3) {
        System.out.printf("%-20s %-20s %10s %n", column1, column2, column3);
    }

}
