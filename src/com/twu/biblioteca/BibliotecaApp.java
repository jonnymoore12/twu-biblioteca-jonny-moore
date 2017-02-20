package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    private Library library = new Library();

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.run();
        bibliotecaApp.listBooks();
    }

    public void run() {
        System.out.println("Welcome to Biblioteca!");
    }

    public void displayMenu() {
//        Scanner reader = new Scanner(System.in);
//        System.out.println("Please choose from the following options (type the corresponding number)");
//        String userInput = reader.next();

        System.out.println("1. List Books");
        System.out.println("2. Checkout Book");
        System.out.println("3. Return Book");
        System.out.println("4. Quit");

    }

    public void listBooks() {

        printBookDetailsColumns();

        for (Book book : library.getBooks()) {
            printBookInfo(book);
        }
    }

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

}
