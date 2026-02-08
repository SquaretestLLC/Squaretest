package com.myapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testLeftGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String left = "left";
        myClassUnderTest.setTheLeft(left);
        assertEquals(left, myClassUnderTest.getTheLeft());
    }

    @Test
    public void testRightGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String right = "right";
        myClassUnderTest.setTheRight(right);
        assertEquals(right, myClassUnderTest.getTheRight());
    }

    @Test
    public void testCombine() {
        // Setup
        final MyClass<String, String> myClassUnderTest = new MyClass<>();

        // Run the test
        final String result = myClassUnderTest.combine();

        // Verify the results
        assertEquals("result", result);
    }
}
