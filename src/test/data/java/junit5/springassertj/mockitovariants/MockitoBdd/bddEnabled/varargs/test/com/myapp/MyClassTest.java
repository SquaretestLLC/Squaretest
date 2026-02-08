package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testDoSomethingCool() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCool("theStrings");

        // Verify the results
    }

    @Test
    void testDoSomethingInC() {
        assertThat(myClassUnderTest.doSomethingInC()).isEqualTo("result");
    }
}
