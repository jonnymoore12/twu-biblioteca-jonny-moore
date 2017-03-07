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
public class CheckoutMovieTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private Library libraryWithUnavailableItems = new Library(new ArrayList(Arrays.asList(
            new Book("Brave New World", "Aldous Huxley", "1931"),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925", false))),
            new ArrayList(Arrays.asList(
                    new Movie("Chungking Express", "Wong Kar Wai", "1994", "10"),
                    new Movie("Alien", "Ridley Scott", "1979", "10", false))));

    private UserInput mockedUserInput = mock(UserInput.class);
    private CheckoutMovie mockedCheckoutMovie = new CheckoutMovie(new Library(), mockedUserInput);

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void execute_promptsUserForValidSelection() {
        when(mockedUserInput.getStringInput()).thenReturn("Not a valid movie").thenReturn("Chungking Express");
        mockedCheckoutMovie.execute();
        assertTrue(outContent.toString().contains("Please select a valid option!"));
    }

    @Test
    public void execute_confirmsSuccessfulMovieCheckouts() {
        when(mockedUserInput.getStringInput()).thenReturn("Chungking Express");
        mockedCheckoutMovie.execute();
        assertTrue(outContent.toString().contains("You have successfully checked out 'Chungking " +
                "Express'. Thank you. Enjoy your movie!"));
    }

    @Test
    public void execute_successfulCheckoutsRemoveMovieFromLibrary() {
        when(mockedUserInput.getStringInput()).thenReturn("Chungking Express");
        Library newLibrary = new Library();
        assertTrue(newLibrary.containsMovie("Chungking Express"));
        CheckoutMovie newMockedCheckoutMovie = new CheckoutMovie(newLibrary, mockedUserInput);
        newMockedCheckoutMovie.execute();
        assertFalse(newLibrary.containsMovie("Chungking Express"));
    }
}