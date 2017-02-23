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

    private List<Movie> movies = new ArrayList(Arrays.asList(new Movie("Chungking Express", "Wong Kar Wai", "1994", "10"),
            new Movie("The Shining", "Stanley Kubrick", "1980", "9"),
            new Movie("The Goonies", "Richard Donner", "1985", "7")));

    public Library(List<Book> books, List<Movie> movies) {
        this.books = books;
        this.movies = movies;
    }

    public Library() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Movie> getMovies() { return movies; }

    public boolean containsBook(String bookTitle) {
        for (Book book : books) {
            if (bookTitle.equals(book.getTitle()) && book.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public boolean containsMovie(String movieName) {
        for (Movie movie : movies) {
            if (movieName.equals(movie.getName()) && movie.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public boolean bookWaitingToBeReturned(String bookTitle) {
        for (Book book : books) {
            if (bookTitle.equals(book.getTitle()) && !book.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public boolean movieWaitingToBeReturned(String bookName) {
        for (Movie movie : movies) {
            if (bookName.equals(movie.getName()) && !movie.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public void removeBook(String bookTitle) {
        for (Book book : books) {
            if (bookTitle.equals(book.getTitle())) {
                book.setAvailability(false);
            }
        }
    }

    public void removeMovie(String movieName) {
        for (Movie movie : movies) {
            if (movieName.equals(movie.getName())) {
                movie.setAvailability(false);
            }
        }
    }

    public void returnBook(String bookTitle) {
        for (Book book : books) {
            if (bookTitle.equals(book.getTitle())) {
                book.setAvailability(true);
            }
        }
    }

    public void returnMovie(String movieName) {
        for (Movie movie : movies) {
            if (movieName.equals(movie.getName())) {
                movie.setAvailability(true);
            }
        }
    }
}
