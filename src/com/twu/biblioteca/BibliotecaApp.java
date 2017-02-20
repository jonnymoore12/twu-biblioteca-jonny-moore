package com.twu.biblioteca;

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
