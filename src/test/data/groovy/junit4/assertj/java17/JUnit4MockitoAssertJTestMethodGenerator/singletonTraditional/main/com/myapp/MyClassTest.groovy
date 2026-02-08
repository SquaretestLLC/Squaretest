package com.myapp

import org.junit.Before
import org.junit.Test

import java.net.Socket

import static org.junit.Assert.assertEquals

class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = MyClass.getInstance()
    }

    @Test
    void testCreateNewConnection() throws Exception {
        // Setup
        final Socket expectedResult = new Socket("host", 80)

        // Run the test
        final Socket result = myClassUnderTest.createNewConnection()

        // Verify the results
        assertEquals(expectedResult, result)
    }

    @Test
    void testGetInstance() {
        // Setup
        final MyClass expectedResult = MyClass.getInstance()

        // Run the test
        final MyClass result = MyClass.getInstance()

        // Verify the results
        assertEquals(expectedResult, result)
    }
}
