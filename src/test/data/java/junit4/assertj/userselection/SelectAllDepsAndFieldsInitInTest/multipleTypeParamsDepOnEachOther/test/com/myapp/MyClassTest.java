package com.myapp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testLeftGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String left = "left";
        myClassUnderTest.setTheLeft(left);
        assertThat(myClassUnderTest.getTheLeft()).isEqualTo(left);
    }

    @Test
    public void testRightGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String right = "right";
        myClassUnderTest.setTheRight(right);
        assertThat(myClassUnderTest.getTheRight()).isEqualTo(right);
    }

    @Test
    public void testCombine() {
        // Setup
        final MyClass<String, String> myClassUnderTest = new MyClass<>();

        // Run the test
        final String result = myClassUnderTest.combine();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }
}
