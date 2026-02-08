package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testCreateFoo() {
        // Setup
        final FooData expectedResult = new FooData("fooIdParam", "fooNameParam", "fooOtherValueParam");

        // Run the test
        final FooData result = myClassUnderTest.createFoo("fooIdParam", "fooNameParam", "fooOtherValueParam");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
