package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.Test

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
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.performGetUpData1("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
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
    void testGetUpperFoo1() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetUpperFoo11() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getUpperFoo1("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetUpperFoo2() {
        def myClassUnderTest = new MyClass(new FooService())
        assertThat(myClassUnderTest.getUpperFoo("key")).isEqualTo("")
    }

    @Test
    void testGetUpperFoo12() {
        def myClassUnderTest = new MyClass(new FooService())
        assertThat(myClassUnderTest.getUpperFoo1("key")).isEqualTo("")
    }
}
