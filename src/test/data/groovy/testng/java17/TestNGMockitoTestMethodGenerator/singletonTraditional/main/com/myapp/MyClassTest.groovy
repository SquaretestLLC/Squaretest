package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.net.Socket

import static org.testng.Assert.assertEquals

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
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
