package com.myapp

import org.testng.annotations.Test

import java.io.File

import static org.testng.Assert.assertEquals

class MyClassTest {

    @Test
    void testFromString() {
        // Setup
        final MyClass expectedResult = MyClass.fromString("uriString")

        // Run the test
        final MyClass result = MyClass.fromString("uriString")

        // Verify the results
        assertEquals(expectedResult, result)
    }

    @Test
    void testFromFile() {
        // Setup
        final File file = new File("filename.txt")
        final MyClass expectedResult = MyClass.fromString("uriString")

        // Run the test
        final MyClass result = MyClass.fromFile(file)

        // Verify the results
        assertEquals(expectedResult, result)
    }

    @Test
    void testFromParts() {
        // Setup
        final MyClass expectedResult = MyClass.fromString("uriString")

        // Run the test
        final MyClass result = MyClass.fromParts("scheme", "ssp", "fragment")

        // Verify the results
        assertEquals(expectedResult, result)
    }
}
