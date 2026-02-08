package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MyClassTest {

    private MyClass<String> myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    public void testGetSomething() {
        assertNull(myClassUnderTest.getSomething());
    }

    @Test
    public void testPutSomething() {
        // Setup
        // Run the test
        myClassUnderTest.putSomething("thingToPut");

        // Verify the results
    }

    @Test
    public void testCountSomethings() {
        assertEquals(0, myClassUnderTest.countSomethings());
    }
}
