package com.myapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    @Test
    void testLeftGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String left = "left";
        myClassUnderTest.setTheLeft(left);
        assertEquals(left, myClassUnderTest.getTheLeft());
    }

    @Test
    void testRightGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String right = "right";
        myClassUnderTest.setTheRight(right);
        assertEquals(right, myClassUnderTest.getTheRight());
    }

    @Test
    void testCombine() {
        // Setup
        final MyClass<String, String> myClassUnderTest = new MyClass<>();

        // Run the test
        final String result = myClassUnderTest.combine();

        // Verify the results
        assertEquals("result", result);
    }
}
