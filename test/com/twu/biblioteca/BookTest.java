package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JonnyM on 20/02/2017.
 */
public class BookTest {

    private Book braveNewWorld = new Book("Brave New World", "Aldous Huxley", "1931", true);
    private Book unavailableBook = new Book("American Psycho", "Bret Easton Ellis", "1991", false);

    @Test
    public void getTitle_displaysTitleOfBook() {
        assertEquals("Brave New World", braveNewWorld.getTitle());
    }

    @Test
    public void getAuthor() {
        assertEquals("Aldous Huxley", braveNewWorld.getAuthor());
    }

    @Test
    public void getYear() {
        assertEquals("1931", braveNewWorld.getYear());
    }

    @Test
    public void isAvailable_booksAreAvailableByDefault() {
        assertTrue(braveNewWorld.isAvailable());
    }

    @Test
    public void isAvailable_returnsFalseIfBookNotAvailable() {
        assertFalse(unavailableBook.isAvailable());
    }

    @Test
    public void setAvailability_canChangeAvailability() {
        assertTrue(braveNewWorld.isAvailable());
        braveNewWorld.setAvailability(false);
        assertFalse(braveNewWorld.isAvailable());
    }
}
