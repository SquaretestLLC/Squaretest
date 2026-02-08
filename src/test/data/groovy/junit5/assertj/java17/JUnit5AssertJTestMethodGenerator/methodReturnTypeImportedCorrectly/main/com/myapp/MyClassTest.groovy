package com.myapp

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.net.Socket
import java.util.HashMap
import java.util.Map

import static org.junit.jupiter.api.Assertions.assertEquals

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
    }

    @Test
    void testCreateStringSocketMap() {
        // Setup
        final Map<String, Socket> expectedResult = new HashMap<>()

        // Run the test
        final Map<String, Socket> result = myClassUnderTest.createStringSocketMap()

        // Verify the results
        assertEquals(expectedResult, result)
    }
}
