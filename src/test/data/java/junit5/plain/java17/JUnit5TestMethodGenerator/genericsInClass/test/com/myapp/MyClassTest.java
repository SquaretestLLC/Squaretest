package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MyClassTest {

    private MyClass<String> myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    void testGetSomething1() {
        assertNull(myClassUnderTest.getSomething());
    }

    @Test
    void testPutSomething1() {
        // Setup
        // Run the test
        myClassUnderTest.putSomething("thingToPut");

        // Verify the results
    }

    @Test
    void testCountSomethings1() {
        assertEquals(0, myClassUnderTest.countSomethings());
    }
}