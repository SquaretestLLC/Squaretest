package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class MyClassTest {

    private MyClass<String> myClassUnderTest;

    @BeforeMethod
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
