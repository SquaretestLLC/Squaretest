package com.myapp;

import com.myapp.bases.SubFooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new SubFooService());
    }

    @Test
    void testGetFoo() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo2() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getFoo2("key");

        // Verify the results
        assertEquals("result", result);
    }
}
