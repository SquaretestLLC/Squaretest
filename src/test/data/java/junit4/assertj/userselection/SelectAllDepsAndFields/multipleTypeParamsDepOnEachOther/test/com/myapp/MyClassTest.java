package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(myClassUnderTest.getTheLeft()).isEqualTo(left);
    }

    @Test
    public void testRightGetterAndSetter() {
        final String right = "right";
        myClassUnderTest.setTheRight(right);
        assertThat(myClassUnderTest.getTheRight()).isEqualTo(right);
    }

    @Test
    public void testCombine() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.combine();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }
}
