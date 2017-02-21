package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JonnyM on 20/02/2017.
 */
public class Library {

    private List<Book> books = new ArrayList(Arrays.asList( new Book("Brave New World", "Aldous Huxley", "1931"),
                            new Book("Lolita", "Vladimir Nabokov", "1955"),
                            new Book("Fight Club", "Chuck Palahniuk", "1996") ));

    public Library(List<Book> books) {
        this.books = books;
    }

    public Library() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public boolean containsBook(String bookTitle) {
        for (Book book : books) {
            // This should be equality but there's a bug:
            if (bookTitle.contains(book.getTitle()) && book.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public void removeBook(String bookTitle) {
        for (Book book : books) {
            if (bookTitle.contains(book.getTitle())) {
                book.markUnavailable();
            }
        }
    }

}
