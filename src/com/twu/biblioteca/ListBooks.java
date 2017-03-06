package com.twu.biblioteca;

import static com.twu.biblioteca.BibliotecaApp.*;

/**
 * Created by jonnytw on 06/03/2017.
 */
public class ListBooks implements Command {

    private Library library;

    ListBooks(Library library) {
        this.library = library;
    }

    public void execute() {
        listBooks();
    }

    public void listBooks() {
        System.out.println("\n\nCheck out the list of books currently at Biblioteca:");

        library.printBookHeaders();

        for (Book book : library.getBooks()) {
            if (book.isAvailable()) {
                library.printBookInfo(book.getTitle(), book.getAuthor(), book.getYear());
            }
        }
    }
}
