package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final BibliotecaApp bibliotecaApp = new BibliotecaApp(new Library(), new UserInput());
    private final Library libraryWithUnavailableBook = new Library(new ArrayList(Arrays.asList(
            new Book("Brave New World", "Aldous Huxley", "1931"),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925", false))));
    private final BibliotecaApp bibliotecaAppWithUnavailableBook =
            new BibliotecaApp(libraryWithUnavailableBook, new UserInput());
    private final UserInput mockedUserInput = mock(UserInput.class);
    private final BibliotecaApp mockedBiblioteca = new BibliotecaApp(new Library(), mockedUserInput);


    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void welcomeMessage_appDisplaysWelcomeMessage() {
        bibliotecaApp.welcomeMessage();
        assertTrue(outContent.toString().contains("Welcome to Biblioteca"));
    }

    @Test
    public void listAllBooks_displaysBooksIncludingTitles() {
        bibliotecaApp.listAllBooks();
        assertTrue(outContent.toString().contains("Brave New World"));
    }

    @Test
    public void listAllBooks_displaysBooksIncludingAuthors() {
        bibliotecaApp.listAllBooks();

    }

    @Test
    public void listAllBooks_displaysBooksIncludingYearsPublished() {
        bibliotecaApp.listAllBooks();
        assertTrue(outContent.toString().contains("1931"));
    }

    @Test
    public void listAllBooks_marksAvailabilityOfBooksAsBoolean() {
        bibliotecaApp.listAllBooks();
        assertTrue(outContent.toString().contains("true"));
    }

    @Test
    public void onlyListAvailableBooks_displaysAvailableBooks() {
        bibliotecaAppWithUnavailableBook.onlyListAvailableBooks();
        assertTrue(outContent.toString().contains("Brave New World"));
    }

    @Test
    public void onlyListAvailableBooks_doesNotDisplayUnavailableBooks() {
        bibliotecaAppWithUnavailableBook.onlyListAvailableBooks();
        assertFalse(outContent.toString().contains("The Great Gatsby"));
    }

    @Test
    public void listBooks_displaysAppropriateColumnsForReadability() {
        bibliotecaApp.listAllBooks();
        assertTrue(outContent.toString().contains("Title"));
    }

    @Test
    public void displayMenuOptions_listsAllMenuOptions() {
        bibliotecaApp.displayMenuOptions();
        assertTrue(outContent.toString().contains("1. List Books"));
        assertTrue(outContent.toString().contains("2. Checkout Book"));
        assertTrue(outContent.toString().contains("3. Return Book"));
        assertTrue(outContent.toString().contains("4. Quit"));
    }

    @Test
    public void invalidSelection_promptsUserForValidSelection() {
        bibliotecaApp.invalidSelection();
        assertTrue(outContent.toString().contains("Please select a valid option!"));
    }

    @Test
    public void checkoutBook_confirmsSuccessfulBookCheckouts() {
        when(mockedUserInput.getStringInput()).thenReturn("Brave New World");
        mockedBiblioteca.checkoutBook();
        assertTrue(outContent.toString().contains("You have successfully checked out 'Brave " +
                                                        "New World'. Thank you! Enjoy your book."));
    }

    @Test
    public void checkoutBook_confirmsUnsuccessfulBookCheckouts() {
        when(mockedUserInput.getStringInput()).thenReturn("Not a valid book").thenReturn("Lolita");
        mockedBiblioteca.checkoutBook();
        assertTrue(outContent.toString().contains("Invalid selection. Please select a valid option!"));
    }

    @Test
    public void checkoutBook_successfulCheckoutsRemoveBookFromLibrary() {
        when(mockedUserInput.getStringInput()).thenReturn("Lolita");
        Library newLibrary = new Library();
        BibliotecaApp newBiblioteca = new BibliotecaApp(newLibrary, mockedUserInput);
        newBiblioteca.checkoutBook();
        assertFalse(newLibrary.containsBook("Lolita"));
    }

    @Test
    public void returnBook_confirmsASuccessfulReturn() {
        when(mockedUserInput.getStringInput()).thenReturn("The Great Gatsby");
        BibliotecaApp bibliotecaWithGatsbyCheckedOut = new BibliotecaApp(libraryWithUnavailableBook, mockedUserInput);
        bibliotecaWithGatsbyCheckedOut.returnBook();
        assertTrue(outContent.toString().contains("Thank you for returning 'The Great Gatsby'."));
    }

    @Test
    public void returnBook_successfulReturnsPlaceBookBackInLibrary() {
        when(mockedUserInput.getStringInput()).thenReturn("The Great Gatsby");
        BibliotecaApp bibliotecaWithGatsbyCheckedOut = new BibliotecaApp(libraryWithUnavailableBook, mockedUserInput);
        bibliotecaWithGatsbyCheckedOut.returnBook();
        assertTrue(libraryWithUnavailableBook.containsBook("The Great Gatsby"));
    }

    @Test
    public void returnBook_booksAlreadyInLibraryCannotBeReturned() {
        when(mockedUserInput.getStringInput()).thenReturn("Brave New World");
        mockedBiblioteca.returnBook();
        assertTrue(outContent.toString().contains("This book is already in the Biblioteca." +
                                                    " You must have the wrong library!"));
    }

    @Test
    public void returnBook_notifiesUserOfInvalidInput() {
        when(mockedUserInput.getStringInput()).thenReturn("Bad input").thenReturn("Brave New World");
        mockedBiblioteca.returnBook();
        assertTrue(outContent.toString().contains("That is not a valid book to return. Please try again."));
    }

    @Test
    public void quit_displaysLeavingMessageToUser() {
        bibliotecaApp.quit();
        assertTrue(outContent.toString().contains("Exiting Biblioteca. See you next time."));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}
