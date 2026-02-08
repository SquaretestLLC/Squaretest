package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = MyClass.getInstance();
    }

    @Test
    public void testGetInstance1() {
        // Setup
        // Run the test
        final MyClass result = MyClass.getInstance();

        // Verify the results
    }

    @Test
    public void testCreateNewConnection1() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}