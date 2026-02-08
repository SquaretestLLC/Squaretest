package com.myapp;

import com.myapp.bases.FooService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(new FooService());
    }

    @Test
    public void testPerformGetUpData() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testPerformGetUpData1() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.performGetUpData1("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetUpperFoo1() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetUpperFoo11() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getUpperFoo1("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetUpperFoo2() {
        assertThat(myClassUnderTest.getUpperFoo("key")).isEqualTo("");
    }

    @Test
    public void testGetUpperFoo12() {
        assertThat(myClassUnderTest.getUpperFoo1("key")).isEqualTo("");
    }
}
