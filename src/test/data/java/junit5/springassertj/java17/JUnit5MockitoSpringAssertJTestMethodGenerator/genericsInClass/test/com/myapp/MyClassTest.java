package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass<String> myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    void testGetSomething1() {
        assertThat(myClassUnderTest.getSomething()).isNull();
    }

    @Test
    void testPutSomething1() {
        // Setup
        // Run the test
        myClassUnderTest.putSomething("thingToPut");

        // Verify the results
    }

    @Test
    void testCountSomethings1() {
        assertThat(myClassUnderTest.countSomethings()).isEqualTo(0);
    }
}