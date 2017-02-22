package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by JonnyM on 20/02/2017.
 */
public class LibraryTest {

    private Library library = new Library();
    private Library libraryWithUnavailableBook = new Library(new ArrayList(Arrays.asList(
            new Book("Brave New World", "Aldous Huxley", "1931"),
            new Book("A Clockwork Orange", "Anthony Burgess", "1962", false ))));


    @Test
    public void containsBook_returnsTrueIfBookInLibraryRecordsAndAvailable() {
        assertTrue(library.containsBook("Lolita"));
    }

    @Test
    public void containsBook_returnsFalseIfBookNotListedInLibraryRecords() {
        assertFalse(library.containsBook("Do Androids Dream of Electric Sheep?"));
    }

    @Test
    public void containsBook_returnsFalseIfBookNotAvailable() {
        assertFalse(libraryWithUnavailableBook.containsBook("A Clockwork Orange"));
    }

    @Test
    public void bookWaitingToBeReturned_returnsTrueIfBookCheckedOut() {
        assertTrue(libraryWithUnavailableBook.bookWaitingToBeReturned("A Clockwork Orange"));
    }

    @Test
    public void bookWaitingToBeReturned_returnsFalseIfBookCurrentlyAvailable() {
        assertFalse(libraryWithUnavailableBook.bookWaitingToBeReturned("Brave New World"));
    }

    @Test
    public void removeBook_notesBookAsUnavailable() {
        assertTrue(library.containsBook("Lolita"));
        library.removeBook("Lolita");
        assertFalse(library.containsBook("Lolita"));
    }

    @Test
    public void returnBook_successfulReturnsPlaceBooksBackInLibraryAsAvailable() {
        libraryWithUnavailableBook.returnBook("A Clockwork Orange");
        assertTrue(libraryWithUnavailableBook.containsBook("A Clockwork Orange"));
    }
}