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
    public void executeMenuSelection_canListAllBooks() {
        when(mockedUserInput.getStringInput()).thenReturn("1");
        String menuChoice = mockedBiblioteca.getUserMenuSelection();
        mockedBiblioteca.executeMenuSelection(menuChoice);
        assertTrue(outContent.toString().contains("Brave New World"));
    }

    @Test
    public void executeMenuSelection_canListAllMovies() {
        when(mockedUserInput.getStringInput()).thenReturn("2");
        String menuChoice = mockedBiblioteca.getUserMenuSelection();
        mockedBiblioteca.executeMenuSelection(menuChoice);
        assertTrue(outContent.toString().contains("Chungking Express"));
    }

//    @Test
//    public void executeMenuSelection_checkoutBooks_promptsUsersForInput() {
//        when(mockedUserInput.getStringInput()).thenReturn("3").thenReturn("Brave New World");
//        String menuChoice = mockedBiblioteca.getUserMenuSelection();
//        mockedBiblioteca.executeMenuSelection(menuChoice);
//        assertTrue(outContent.toString().contains("Please enter the TITLE of the book you wish to checkout"));
//    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}
