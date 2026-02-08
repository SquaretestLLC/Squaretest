package com.myapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testLeftGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String left = "left";
        myClassUnderTest.setTheLeft(left);
        assertThat(myClassUnderTest.getTheLeft()).isEqualTo(left);
    }

    @Test
    void testRightGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String right = "right";
        myClassUnderTest.setTheRight(right);
        assertThat(myClassUnderTest.getTheRight()).isEqualTo(right);
    }

    @Test
    void testCombine() {
        // Setup
        final MyClass<String, String> myClassUnderTest = new MyClass<>();

        // Run the test
        final String result = myClassUnderTest.combine();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }
}
