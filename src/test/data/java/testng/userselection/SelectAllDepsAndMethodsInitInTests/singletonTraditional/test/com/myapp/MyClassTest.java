package com.myapp;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNull;

public class MyClassTest {

    @Test
    public void testGetInstance() {
        // Setup
        // Run the test
        final MyClass result = MyClass.getInstance();

        // Verify the results
    }

    @Test
    public void testCreateNewConnection() {
        final MyClass myClassUnderTest = MyClass.getInstance();
        assertNull(myClassUnderTest.createNewConnection());
    }
}
