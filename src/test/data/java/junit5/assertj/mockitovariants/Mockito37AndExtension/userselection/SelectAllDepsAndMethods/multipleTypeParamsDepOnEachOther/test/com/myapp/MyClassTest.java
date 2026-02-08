package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(myClassUnderTest.getTheLeft()).isEqualTo(left);
    }

    @Test
    void testRightGetterAndSetter() {
        final String right = "right";
        myClassUnderTest.setTheRight(right);
        assertThat(myClassUnderTest.getTheRight()).isEqualTo(right);
    }

    @Test
    void testCombine() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.combine();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }
}
