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
    void testDoSomethingCool1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCool("theStrings");

        // Verify the results
    }

    @Test
    void testDoSomethingCoolWithChars1() {
        // Setup
        // Run the test
        myClassUnderTest.doSomethingCoolWithChars('a');

        // Verify the results
    }

    @Test
    void testDoSomethingCoolWithTables1() {
        myClassUnderTest.doSomethingCoolWithTables(new int[][]{{0}});
    }

    @Test
    void testDoSomethingInC1() {
        assertThat(myClassUnderTest.doSomethingInC()).isEqualTo("result");
    }
}