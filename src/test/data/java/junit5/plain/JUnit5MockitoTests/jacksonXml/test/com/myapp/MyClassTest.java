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
    void testMakeFoo() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");
        expectedResult.setFirstProp("firstProp");
        expectedResult.setSecondProp("secondProp");
        expectedResult.setThirdProp("thirdProp");
        expectedResult.setFourthProp("fourthProp");
        expectedResult.setFifthProp("fifthProp");

        // Run the test
        final FooData result = myClassUnderTest.makeFoo();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
