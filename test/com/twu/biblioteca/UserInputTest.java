package com.twu.biblioteca;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by JonnyM on 20/02/2017.
 */
public class UserInputTest {

    private UserInput MockInput = Mockito.mock(UserInput.class);

    @Test
    public void getStringInput_ReturnsUserInputAsString() {
        Mockito.when(MockInput.getStringInput()).thenReturn("Yo yo yo");
        assertEquals("Yo yo yo", MockInput.getStringInput());
    }
}