package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomethingCool1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCool("theStrings");

        // Verify the results
    }

    @Test
    public void testDoSomethingCoolWithChars1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCoolWithChars('a');

        // Verify the results
    }

    @Test
    public void testDoSomethingCoolWithTables1() {
        myClassUnderTest.doSomethingCoolWithTables(new int[][]{{0}});
    }

    @Test
    public void testDoSomethingInC1() {
        assertEquals("result", myClassUnderTest.doSomethingInC());
    }
}