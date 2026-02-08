package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    private MyClass<String> myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    public void testGetSomething1() {
        assertThat(myClassUnderTest.getSomething()).isNull();
    }

    @Test
    public void testPutSomething1() {
        // Setup
        // Run the test
        myClassUnderTest.putSomething("thingToPut");

        // Verify the results
    }

    @Test
    public void testCountSomethings1() {
        assertThat(myClassUnderTest.countSomethings()).isEqualTo(0);
    }
}