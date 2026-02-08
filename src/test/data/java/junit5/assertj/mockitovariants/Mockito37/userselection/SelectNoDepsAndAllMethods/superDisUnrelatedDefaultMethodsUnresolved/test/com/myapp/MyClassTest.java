package com.myapp;

import com.myapp.bases.FooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new FooService());
    }

    @Test
    void testPerformGetUpData() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testPerformGetUpData1() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.performGetUpData1("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetFoo() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetUpperFoo1() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetUpperFoo11() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getUpperFoo1("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetUpperFoo2() {
        assertThat(myClassUnderTest.getUpperFoo("key")).isEqualTo("");
    }

    @Test
    void testGetUpperFoo12() {
        assertThat(myClassUnderTest.getUpperFoo1("key")).isEqualTo("");
    }
}
