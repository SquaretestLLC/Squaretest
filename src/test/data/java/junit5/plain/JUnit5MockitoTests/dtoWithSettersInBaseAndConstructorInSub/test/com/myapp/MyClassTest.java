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
    void testCreateFooSub() {
        // Setup
        final FooSub expectedResult = new FooSub("fooBaseIdParam", "fooBaseValueParam", "fooBaseOtherValueParam",
                "fooSubValueParam", "fooSubOtherValueParam");

        // Run the test
        final FooSub result = myClassUnderTest.createFooSub("fooBaseIdParam", "fooBaseValueParam",
                "fooBaseOtherValueParam", "fooSubValueParam", "fooSubOtherValueParam");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
