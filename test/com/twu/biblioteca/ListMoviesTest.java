package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

/**
 * Created by jonnytw on 06/03/2017.
 */
public class ListMoviesTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private Library libraryWithUnavailableItems = new Library(new ArrayList(Arrays.asList(
            new Book("Brave New World", "Aldous Huxley", "1931"),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925", false))),
            new ArrayList(Arrays.asList(
                    new Movie("Chungking Express", "Wong Kar Wai", "1994", "10"),
                    new Movie("Alien", "Ridley Scott", "1979", "10", false))));
    private ListMovies listMovies = new ListMovies(libraryWithUnavailableItems);

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void execute_displaysAllMovieInfo() {
        listMovies.execute();
        assertTrue(outContent.toString().contains("Chungking Express"));
        assertTrue(outContent.toString().contains("Wong Kar Wai"));
        assertTrue(outContent.toString().contains("1994"));
        assertTrue(outContent.toString().contains("10"));
    }

    @Test
    public void execute_doesNotDisplayUnavailableMovies() {
        listMovies.execute();
        assertFalse(outContent.toString().contains("The Great Gatsby"));
    }

}