package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JonnyM on 23/02/2017.
 */
public class UserTest {

    User jonny = new User("Jonny", "jonny@mail.com", "5551234", "123-4567", "password");

    @Test
    public void getUsername_returnsUsername() {
        assertEquals("Jonny", jonny.getUsername());
    }

    @Test
    public void getEmail_returnsEmail() {
        assertEquals("jonny@mail.com", jonny.getEmail());
    }

    @Test
    public void getPhoneNumber_returnsPhoneNumber() {
        assertEquals("5551234", jonny.getPhoneNumber());
    }

    @Test
    public void getLibraryNumber_returnslibraryNumber() {
        assertEquals("123-4567", jonny.getLibraryNumber());
    }

    @Test
    public void getPassword_returnsPassword() {
        assertEquals("password", jonny.getPassword());
    }
}