package com.twu.biblioteca;

public class BibliotecaApp {

    private Library library = new Library();

    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.run();
    }

    public void run() {
        System.out.println("Welcome to Biblioteca!");
    }

    public void listBooks() {

        for (Book book : library.getBooks()) {
            System.out.print(book.getTitle());
            System.out.print(book.getAuthor());
            System.out.print(book.getYear());
        }
    }
}
