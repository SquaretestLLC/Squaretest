package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.net.Socket
import java.util.HashMap
import java.util.Map

import static org.testng.Assert.assertEquals

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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
