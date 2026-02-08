package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new FooService())
    }

    @Test
    void testPerformGetUpData() {
        // Setup
        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testPerformGetUpData1() {
        assertThat(myClassUnderTest.performGetUpData1("data")).isEqualTo("result")
    }

    @Test
    void testGetFoo() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetFoo2() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetUpperFoo() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetUpperFoo1() {
        assertThat(myClassUnderTest.getUpperFoo1("key")).isEqualTo("result")
    }
}
