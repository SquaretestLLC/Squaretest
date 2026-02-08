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
        assertEquals(new Socket("host", 80), myClassUnderTest.createNewConnection());
    }

    @Test
    void testGetInstance() {
        // Setup
        // Run the test
        final MyClass result = MyClass.getInstance();

        // Verify the results
    }
}
