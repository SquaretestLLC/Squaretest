package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass<String, String> myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    void testLeftGetterAndSetter() {
        final String left = "left";
        myClassUnderTest.setTheLeft(left);
        assertEquals(left, myClassUnderTest.getTheLeft());
    }

    @Test
    void testRightGetterAndSetter() {
        final String right = "right";
        myClassUnderTest.setTheRight(right);
        assertEquals(right, myClassUnderTest.getTheRight());
    }

    @Test
    void testCombine() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.combine();

        // Verify the results
        assertEquals("result", result);
    }
}
