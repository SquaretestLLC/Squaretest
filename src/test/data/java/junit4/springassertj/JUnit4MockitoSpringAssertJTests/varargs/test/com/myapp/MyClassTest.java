package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    public void testDoSomethingCool() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCool("theStrings");

        // Verify the results
    }

    @Test
    public void testDoSomethingCoolWithChars() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCoolWithChars('a');

        // Verify the results
    }

    @Test
    public void testDoSomethingCoolWithTables() {
        myClassUnderTest.doSomethingCoolWithTables(new int[][]{{0}});
    }

    @Test
    public void testDoSomethingInC() {
        assertThat(myClassUnderTest.doSomethingInC()).isEqualTo("result");
    }
}
