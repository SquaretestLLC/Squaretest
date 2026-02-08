package com.myapp;

import com.myapp.bases.FooService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testPerformGetUpData() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.performGetUpData("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testPerformGetUpData1() {
        final MyClass myClassUnderTest = new MyClass(new FooService());
        assertThat(myClassUnderTest.performGetUpData1("data")).isEqualTo("result");
    }

    @Test
    void testGetFoo() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getFoo("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetUpperFoo() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(new FooService());

        // Run the test
        final String result = myClassUnderTest.getUpperFoo("data");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetUpperFoo1() {
        final MyClass myClassUnderTest = new MyClass(new FooService());
        assertThat(myClassUnderTest.getUpperFoo1("key")).isEqualTo("result");
    }
}
