package com.myapp;

import com.squaretest.supertypes.base.FooService;
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
    public void testPerformGetUpData2() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testPerformGetUpData11() {
        assertThat(myClassUnderTest.performGetUpData1("data")).isEqualTo("result");
    }

    @Test
    public void testGetFoo1() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo21() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getFoo2("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetUpperFoo2() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetUpperFoo11() {
        assertThat(myClassUnderTest.getUpperFoo1("key")).isEqualTo("result");
    }
}