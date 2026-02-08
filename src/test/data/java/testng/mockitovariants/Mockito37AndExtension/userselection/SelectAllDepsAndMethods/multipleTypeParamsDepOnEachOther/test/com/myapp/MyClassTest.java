package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MyClassTest {

    private MyClass<String, String> myClassUnderTest;

    @BeforeMethod
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
