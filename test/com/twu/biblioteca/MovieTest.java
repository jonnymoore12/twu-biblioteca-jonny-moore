package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JonnyM on 22/02/2017.
 */
public class MovieTest {
    Movie rashomon = new Movie("Rashomon", "Akira Kurosawa", "1950", "9");
    Movie persona = new Movie("Persona", "Ingmar Bergman", "1966", "9", false);


    @Test
    public void getName_returnsMovieName() {
        assertEquals("Rashomon", rashomon.getName());
    }

    @Test
    public void getYear_returnsMovieYear() {
        assertEquals("1950", rashomon.getYear());
    }

    @Test
    public void getDirector_returnsMovieDirector() {
        assertEquals("Akira Kurosawa", rashomon.getDirector());
    }

    @Test
    public void getRating_returnsMovieRating() {
        assertEquals("9", rashomon.getRating());
    }

    @Test
    public void isAvailable_moviesAreAvailableByDefault() {
        assertEquals(true, rashomon.isAvailable());
    }

    @Test
    public void isAvailable_returnsFalseIfMovieIsUnavailable() {
        assertEquals(false, persona.isAvailable());
    }

    @Test
    public void setAvailability_canChangeAvailability() {
        assertTrue(rashomon.isAvailable());
        rashomon.setAvailability(false);
        assertFalse(rashomon.isAvailable());
    }
}