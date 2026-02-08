package com.myapp

import com.squaretest.supertypes.base.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    @Test
    void testPerformGetUpData() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData1() {
        def myClassUnderTest = new MyClass(new FooService())
        assertThat(myClassUnderTest.performGetUpData1("data")).isEqualTo("result")
    }

    @Test
    void testGetFoo() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo2() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetUpperFoo() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetUpperFoo1() {
        def myClassUnderTest = new MyClass(new FooService())
        assertThat(myClassUnderTest.getUpperFoo1("key")).isEqualTo("result")
    }
}
