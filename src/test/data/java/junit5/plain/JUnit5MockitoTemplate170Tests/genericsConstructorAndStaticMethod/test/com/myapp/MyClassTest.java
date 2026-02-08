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
    void testCreateFooData() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        final ResponseWrapper<FooData> expectedResult = new ResponseWrapper<>(fooData, 0);

        // Run the test
        final ResponseWrapper<FooData> result = myClassUnderTest.createFooData();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
