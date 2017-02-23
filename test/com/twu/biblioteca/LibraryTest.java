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
    private Library libraryWithUnavailableItems = new Library(new ArrayList(Arrays.asList(
            new Book("Brave New World", "Aldous Huxley", "1931"),
            new Book("A Clockwork Orange", "Anthony Burgess", "1962", false ))),
            new ArrayList(Arrays.asList(
                    new Movie("Chungking Express", "Wong Kar Wai", "1994", "10"),
                    new Movie("The Shining", "Stanley Kubrick", "1980", "9", false))));

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
        assertFalse(libraryWithUnavailableItems.containsBook("A Clockwork Orange"));
    }

    @Test
    public void containsMovie_returnsTrueIfBookInLibraryAndAvailable() {
        assertTrue(library.containsMovie("Chungking Express"));
    }

    @Test
    public void containsMovie_returnsFalseIfMovieNotListedInLibraryRecrods() {
        assertFalse(library.containsMovie("Mulholland Drive"));
    }

    @Test
    public void containsMovie_returnsFalseIfMovieNotAvailable() {
        assertFalse(libraryWithUnavailableItems.containsMovie("The Shining"));
    }

    @Test
    public void bookWaitingToBeReturned_returnsTrueIfBookCheckedOut() {
        assertTrue(libraryWithUnavailableItems.bookWaitingToBeReturned("A Clockwork Orange"));
    }

    @Test
    public void bookWaitingToBeReturned_returnsFalseIfBookCurrentlyAvailable() {
        assertFalse(libraryWithUnavailableItems.bookWaitingToBeReturned("Brave New World"));
    }

    @Test
    public void bookWaitingToBeReturned_returnsFalseIfBookNotListedAtLibrary() {
        assertFalse(libraryWithUnavailableItems.bookWaitingToBeReturned("Not our book"));
    }

    @Test
    public void movieWaitingToBeReturned_returnsTrueIfBookCheckedOut() {
        assertTrue(libraryWithUnavailableItems.movieWaitingToBeReturned("The Shining"));
    }

    @Test
    public void movieWaitingToBeReturned_returnsFalseIfBookCurrentlyAvailable() {
        assertFalse(libraryWithUnavailableItems.movieWaitingToBeReturned("Chungking Express"));
    }

    @Test
    public void movieWaitingToBeReturned_returnsFalseIfMovieNotListedAtLibrary() {
        assertFalse(libraryWithUnavailableItems.movieWaitingToBeReturned("Not our movie"));
    }

    @Test
    public void removeBook_notesBookAsUnavailable() {
        assertTrue(library.containsBook("Lolita"));
        library.removeBook("Lolita");
        assertFalse(library.containsBook("Lolita"));
    }

    @Test
    public void removeMovie_notesMovieAsUnavailable() {
        assertTrue(library.containsMovie("Chungking Express"));
        library.removeMovie("Chungking Express");
        assertFalse(library.containsBook("Chungking Express"));
    }

    @Test
    public void returnBook_successfulReturnsPlaceBooksBackInLibraryAsAvailable() {
        assertFalse(libraryWithUnavailableItems.containsBook("A Clockwork Orange"));
        libraryWithUnavailableItems.returnBook("A Clockwork Orange");
        assertTrue(libraryWithUnavailableItems.containsBook("A Clockwork Orange"));
    }
}