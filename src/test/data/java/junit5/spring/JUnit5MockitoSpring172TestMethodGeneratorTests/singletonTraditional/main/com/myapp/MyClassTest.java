package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = MyClass.getInstance();
    }

    @Test
    void testCreateNewConnection() throws Exception {
        // Setup
        final Socket expectedResult = new Socket("host", 80);

        // Run the test
        final Socket result = myClassUnderTest.createNewConnection();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetInstance() {
        // Setup
        final MyClass expectedResult = MyClass.getInstance();

        // Run the test
        final MyClass result = MyClass.getInstance();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
