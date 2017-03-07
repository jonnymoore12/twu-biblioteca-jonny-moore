package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by JonnyM on 23/02/2017.
 */
public class UserAccountTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private User jonny = new User("Jonny", "jonny@mail.com", "5551234", "123-4567", "password");
    private User leo = new User("Leo", "leo@mail.com", "1234567", "111-1111", "password");
    private UserAccount account = new UserAccount();
    private UserAccount accountTwoUsers = new UserAccount(new ArrayList(Arrays.asList(jonny, leo)));


    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void currentUserIsNullByDefault()  {
        assertEquals(null, account.getCurrentUser());
    }

    @Test
    public void logIn_logsAParticularUserIn() {
        account.logIn(jonny);
        assertEquals(jonny, account.getCurrentUser());
    }

    @Test
    public void verifyLogin_returnsFalseIfLibraryNumberAndPasswordDoNotMatch() {
        assertFalse(account.verifyLogin("123-4567", "wrong, very wrong password"));
    }

    @Test
    public void verifyLogin_returnsTrueIfLibraryNumberAndPasswordMatch()  {
        assertTrue(account.verifyLogin("123-4567", "password"));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}