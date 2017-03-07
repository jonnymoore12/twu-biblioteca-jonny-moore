package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by jonnytw on 07/03/2017.
 */
public class DisplayUserInfoTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private User jonny = new User("Jonny", "jonny@mail.com", "5551234", "123-4567", "password");
    private DisplayUserInfo info = new DisplayUserInfo(jonny);

    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void execute_displaysUserInfoForCurrentUser() {
        info.execute();
        assertTrue(outContent.toString().contains("Jonny"));
        assertTrue(outContent.toString().contains("jonny@mail.com"));
        assertTrue(outContent.toString().contains("5551234"));
    }
}