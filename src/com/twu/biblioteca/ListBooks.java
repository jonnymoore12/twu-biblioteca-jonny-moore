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
        library.listBooks();
    }
}
