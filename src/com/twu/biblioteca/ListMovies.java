package com.twu.biblioteca;

/**
 * Created by jonnytw on 06/03/2017.
 */
public class ListMovies implements Command {

    private Library library;

    ListMovies(Library library) {
        this.library = library;
    }

    public void execute() {
        listMovies();
    }

    public void listMovies() {
        System.out.println("\n\nThese are the movies currently available at Biblioteca:");

        library.printMovieHeaders();

        for (Movie movie : library.getMovies()) {
            if (movie.isAvailable()) {
                library.printMovieInfo(movie.getName(), movie.getDirector(), movie.getYear(), movie.getRating());
            }
        }
    }
}
