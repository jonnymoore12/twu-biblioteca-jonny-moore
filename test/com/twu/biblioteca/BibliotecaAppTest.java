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

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private BibliotecaApp bibliotecaApp = new BibliotecaApp(new Library(), new UserInput());
    private Library libraryWithUnavailableItems = new Library(new ArrayList(Arrays.asList(
            new Book("Brave New World", "Aldous Huxley", "1931"),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925", false))),
            new ArrayList(Arrays.asList(
                    new Movie("Chungking Express", "Wong Kar Wai", "1994", "10"),
                    new Movie("Alien", "Ridley Scott", "1979", "10", false))));
    private BibliotecaApp bibliotecaAppWithUnavailableItems =
            new BibliotecaApp(libraryWithUnavailableItems, new UserInput());
    private UserAccount userAccount = new UserAccount();
    private UserInput mockedUserInput = mock(UserInput.class);
    private BibliotecaApp mockedBiblioteca = new BibliotecaApp(new Library(), userAccount, mockedUserInput);

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
    public void login_confirmsSuccessfulLogin() {
        when(mockedUserInput.getStringInput()).thenReturn("123-4567").thenReturn("password");
        mockedBiblioteca.login();
        assertTrue(outContent.toString().contains("Login successful. Welcome back to Biblioteca!"));
    }

    @Test
    public void login_successfulLoginsUpdateCurrentUserInfo() {
        when(mockedUserInput.getStringInput()).thenReturn("123-4567").thenReturn("password");
        mockedBiblioteca.login();
        mockedBiblioteca.showUserInfo();
        assertTrue(outContent.toString().contains("Jonny"));
    }

    @Test
    public void login_notifiesUserOfInvalidCredentials() {
        when(mockedUserInput.getStringInput()).thenReturn("123-4567").thenReturn("bad bad password")
                                    .thenReturn("123-4567").thenReturn("password").thenReturn("7");;
        mockedBiblioteca.login();
        assertTrue(outContent.toString().contains("I'm afraid something went wrong. Login unsuccessful."));
    }

    @Test
    public void displayMenuOptions_listsAllMenuOptions() {
        bibliotecaApp.displayMenuOptions();
        assertTrue(outContent.toString().contains("1. List Books"));
        assertTrue(outContent.toString().contains("2. List Movies"));
        assertTrue(outContent.toString().contains("3. Checkout Book"));
        assertTrue(outContent.toString().contains("4. Checkout Movie"));
        assertTrue(outContent.toString().contains("5. Return Book"));
        assertTrue(outContent.toString().contains("6. Return Movie"));
        assertTrue(outContent.toString().contains("7. Display User Info"));
        assertTrue(outContent.toString().contains("8. Quit"));
    }

    @Test
    public void listBooks_displaysAllBookInfo() {
        bibliotecaApp.listBooks();
        assertTrue(outContent.toString().contains("Brave New World"));
        assertTrue(outContent.toString().contains("Aldous Huxley"));
        assertTrue(outContent.toString().contains("1931"));
    }

    @Test
    public void listBooks_doesNotDisplayUnavailableBooks() {
        bibliotecaAppWithUnavailableItems.listBooks();
        assertFalse(outContent.toString().contains("The Great Gatsby"));
    }

    @Test
    public void listMovies_displaysAllMovieInfo() {
        bibliotecaApp.listMovies();
        assertTrue(outContent.toString().contains("Chungking Express"));
        assertTrue(outContent.toString().contains("Wong Kar Wai"));
        assertTrue(outContent.toString().contains("1994"));
        assertTrue(outContent.toString().contains("10"));
    }

    @Test
    public void listMovies_doesNotDisplayUnavailableMovies() {
        bibliotecaAppWithUnavailableItems.listMovies();
        assertFalse(outContent.toString().contains("Alien"));
    }

    @Test
    public void checkoutBook_promptsUserForValidSelection() {
        when(mockedUserInput.getStringInput()).thenReturn("Brain New Wooooorld").thenReturn("Brave New World");
        mockedBiblioteca.checkoutBook();
        assertTrue(outContent.toString().contains("Please select a valid option!"));
    }

    @Test
    public void checkoutBook_confirmsSuccessfulBookCheckouts() {
        when(mockedUserInput.getStringInput()).thenReturn("Brave New World");
        mockedBiblioteca.checkoutBook();
        assertTrue(outContent.toString().contains("You have successfully checked out 'Brave " +
                                                        "New World'. Thank you. Enjoy your book!"));
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
    public void checkoutMovie_confirmsSuccessfulMovieCheckouts() {
        when(mockedUserInput.getStringInput()).thenReturn("Chungking Express");
        mockedBiblioteca.checkoutMovie();
        assertTrue(outContent.toString().contains("You have successfully checked out 'Chungking Express'." +
                                                                            " Thank you. Enjoy your movie!"));
    }

    @Test
    public void checkoutMovie_confirmsUnsuccessfulMovieCheckouts() {
        when(mockedUserInput.getStringInput()).thenReturn("Not a valid movie").thenReturn("Chungking Express");
        mockedBiblioteca.checkoutMovie();
        assertTrue(outContent.toString().contains("Invalid selection. Please select a valid option!"));
    }

    @Test
    public void checkoutMovie_successfulCheckoutsRemoveMovieFromLibrary() {
        when(mockedUserInput.getStringInput()).thenReturn("Chungking Express");
        Library tempLibrary = new Library();
        BibliotecaApp newBiblioteca = new BibliotecaApp(tempLibrary, mockedUserInput);
        newBiblioteca.checkoutMovie();
        assertFalse(tempLibrary.containsBook("Chungking Express"));
    }

    @Test
    public void checkoutMovie_promptsUserForValidSelection() {
        when(mockedUserInput.getStringInput()).thenReturn("The Boonies").thenReturn("The Goonies");
        mockedBiblioteca.checkoutMovie();
        assertTrue(outContent.toString().contains("Please select a valid option!"));
    }

    @Test
    public void returnBook_confirmsASuccessfulReturn() {
        when(mockedUserInput.getStringInput()).thenReturn("The Great Gatsby");
        BibliotecaApp bibliotecaWithGatsbyCheckedOut = new BibliotecaApp(libraryWithUnavailableItems, mockedUserInput);
        bibliotecaWithGatsbyCheckedOut.returnBook();
        assertTrue(outContent.toString().contains("Thank you for returning 'The Great Gatsby'."));
    }

    @Test
    public void returnBook_successfulReturnsPlaceBookBackInLibrary() {
        when(mockedUserInput.getStringInput()).thenReturn("The Great Gatsby");
        BibliotecaApp bibliotecaWithGatsbyCheckedOut = new BibliotecaApp(libraryWithUnavailableItems, mockedUserInput);
        bibliotecaWithGatsbyCheckedOut.returnBook();
        assertTrue(libraryWithUnavailableItems.containsBook("The Great Gatsby"));
    }

    @Test
    public void returnBook_booksAlreadyInLibraryCannotBeReturned() {
        when(mockedUserInput.getStringInput()).thenReturn("Brave New World");
        mockedBiblioteca.returnBook();
        assertTrue(outContent.toString().contains("This book is already in the Biblioteca." +
                                                    " You must have the wrong library!"));
    }

    @Test
    public void returnBook_notifiesUsersOfInvalidInput() {
        when(mockedUserInput.getStringInput()).thenReturn("Bad input").thenReturn("Brave New World");
        mockedBiblioteca.returnBook();
        assertTrue(outContent.toString().contains("That is not a valid book to return. Please try again."));
    }

    @Test
    public void returnMovie_confirmsASuccessfulReturn() {
        when(mockedUserInput.getStringInput()).thenReturn("Alien");
        BibliotecaApp bibliotecaWithAlienCheckedOut = new BibliotecaApp(libraryWithUnavailableItems, mockedUserInput);
        bibliotecaWithAlienCheckedOut.returnMovie();
        assertTrue(outContent.toString().contains("Thank you for returning 'Alien'."));
    }

    @Test
    public void returnBook_successfulReturnsMovieBackInLibrary() {
        when(mockedUserInput.getStringInput()).thenReturn("Alien");
        BibliotecaApp bibliotecaWithAlienCheckedOut = new BibliotecaApp(libraryWithUnavailableItems, mockedUserInput);
        bibliotecaWithAlienCheckedOut.returnMovie();
        assertTrue(libraryWithUnavailableItems.containsMovie("Alien"));
    }

    @Test
    public void returnMovie_moviesAlreadyInLibraryCannotBeReturned() {
        when(mockedUserInput.getStringInput()).thenReturn("Chungking Express");
        mockedBiblioteca.returnMovie();
        assertTrue(outContent.toString().contains("This movie is already in the Biblioteca." +
                                                        " You must have the wrong library!"));
    }

    @Test
    public void returnMovie_notifiesUsersOfInvalidInput() {
        when(mockedUserInput.getStringInput()).thenReturn("Bad input").thenReturn("Chungking Express");
        bibliotecaApp.returnMovie();
        assertTrue(outContent.toString().contains("That is not a valid movie to return! Please try again"));
    }

    @Test
    public void quit_displaysLeavingMessageToUser() {
        bibliotecaApp.quit();
        assertTrue(outContent.toString().contains("Thanks for using Biblioteca. See you next time."));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}
