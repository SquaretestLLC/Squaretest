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
    void testMakePair() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        final Pair<String, FooData> expectedResult = new Pair<>("idParam", new Wrapper<>(fooData));

        // Run the test
        final Pair<String, FooData> result = myClassUnderTest.makePair("idParam");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
