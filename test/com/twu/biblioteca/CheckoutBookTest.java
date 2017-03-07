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
public class CheckoutBookTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private Library libraryWithUnavailableItems = new Library(new ArrayList(Arrays.asList(
            new Book("Brave New World", "Aldous Huxley", "1931"),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925", false))),
            new ArrayList(Arrays.asList(
                    new Movie("Chungking Express", "Wong Kar Wai", "1994", "10"),
                    new Movie("Alien", "Ridley Scott", "1979", "10", false))));

    private UserInput mockedUserInput = mock(UserInput.class);
    private CheckoutBook mockedCheckoutBook = new CheckoutBook(new Library(), mockedUserInput);

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void execute_promptsUserForValidSelection() {
        when(mockedUserInput.getStringInput()).thenReturn("Not a valid book").thenReturn("Brave New World");
        mockedCheckoutBook.execute();
        assertTrue(outContent.toString().contains("Please select a valid option!"));
    }

    @Test
    public void execute_confirmsSuccessfulBookCheckouts() {
        when(mockedUserInput.getStringInput()).thenReturn("Brave New World");
        mockedCheckoutBook.execute();
        assertTrue(outContent.toString().contains("You have successfully checked out 'Brave " +
                "New World'. Thank you. Enjoy your book!"));
    }

    @Test
    public void execute_successfulCheckoutsRemoveBookFromLibrary() {
        when(mockedUserInput.getStringInput()).thenReturn("Lolita");
        Library newLibrary = new Library();
        assertTrue(newLibrary.containsBook("Lolita"));
        CheckoutBook newMockedCheckoutBook = new CheckoutBook(newLibrary, mockedUserInput);
        newMockedCheckoutBook.execute();
        assertFalse(newLibrary.containsBook("Lolita"));
    }
}