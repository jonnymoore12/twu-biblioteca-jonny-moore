package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jonnytw on 06/03/2017.
 */
public class ReturnMovieTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private Library libraryWithUnavailableItems = new Library(new ArrayList(Arrays.asList(
            new Book("Brave New World", "Aldous Huxley", "1931"),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925", false))),
            new ArrayList(Arrays.asList(
                    new Movie("Chungking Express", "Wong Kar Wai", "1994", "10"),
                    new Movie("Alien", "Ridley Scott", "1979", "10", false))));

    private UserInput mockedUserInput = mock(UserInput.class);
    private ReturnMovie mockedReturnMovie = new ReturnMovie(libraryWithUnavailableItems, mockedUserInput);

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void execute_confirmsASuccessfulReturn() {
        when(mockedUserInput.getStringInput()).thenReturn("Alien");
        mockedReturnMovie.execute();
        assertTrue(outContent.toString().contains("Thank you for returning 'Alien'."));
    }

    @Test
    public void execute_successfulReturnsPlaceBookBackInLibrary() {
        when(mockedUserInput.getStringInput()).thenReturn("Alien");
        assertFalse(libraryWithUnavailableItems.containsMovie("Alien"));
        mockedReturnMovie.execute();
        assertTrue(libraryWithUnavailableItems.containsMovie("Alien"));
    }

    @Test
    public void execute_booksAlreadyInLibraryCannotBeReturned() {
        when(mockedUserInput.getStringInput()).thenReturn("Chungking Express");
        mockedReturnMovie.execute();
        assertTrue(outContent.toString().contains("This movie is already in the Biblioteca." +
                " You must have the wrong library!"));
    }

    @Test
    public void execute_notifiesUsersOfInvalidInput() {
        when(mockedUserInput.getStringInput()).thenReturn("Bad input").thenReturn("Alien");
        mockedReturnMovie.execute();
        assertTrue(outContent.toString().contains("That is not a valid movie to return. Please try again."));
    }

}