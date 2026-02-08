package com.myapp

import org.junit.Test

import java.io.IOException
import java.net.Socket

import static org.junit.Assert.assertEquals

class MyClassTest {

    @Test
    void testCreateNewConnection() throws Exception {
        // Setup
        final Socket expectedResult = new Socket("host", 80)

        // Run the test
        final Socket result = MyClass.INSTANCE.createNewConnection()

        // Verify the results
        assertEquals(expectedResult, result)
    }

    @Test(expected = IOException.class)
    void testCreateNewConnection_ThrowsIOException() throws Exception {
        // Setup
        // Run the test
        MyClass.INSTANCE.createNewConnection()
    }

    @Test
    void testDoSomething() {
        // Run the test
        MyClass.INSTANCE.doSomething()

        // Verify the results
    }
}
