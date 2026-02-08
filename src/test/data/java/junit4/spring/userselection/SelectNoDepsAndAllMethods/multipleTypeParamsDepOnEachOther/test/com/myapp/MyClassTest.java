package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    private MyClass<String, String> myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    public void testLeftGetterAndSetter() {
        final String left = "left";
        myClassUnderTest.setTheLeft(left);
        assertEquals(left, myClassUnderTest.getTheLeft());
    }

    @Test
    public void testRightGetterAndSetter() {
        final String right = "right";
        myClassUnderTest.setTheRight(right);
        assertEquals(right, myClassUnderTest.getTheRight());
    }

    @Test
    public void testCombine() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.combine();

        // Verify the results
        assertEquals("result", result);
    }
}
