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
    public void testGetSomething() {
        assertThat(myClassUnderTest.getSomething()).isNull();
    }

    @Test
    public void testPutSomething() {
        // Setup
        // Run the test
        myClassUnderTest.putSomething("thingToPut");

        // Verify the results
    }

    @Test
    public void testCountSomethings() {
        assertThat(myClassUnderTest.countSomethings()).isEqualTo(0);
    }
}
