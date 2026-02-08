package com.myapp;

import org.apache.commons.lang3.tuple.Pair;
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
    void testCreatePair() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        final Pair<String, FooData> expectedResult = Pair.of("left", fooData);

        // Run the test
        final Pair<String, FooData> result = myClassUnderTest.createPair("idParam");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
