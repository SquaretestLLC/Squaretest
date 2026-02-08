package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
        assert "result" == result
    }

    @Test
    void testPerformGetUpData1() {
        assert "result" == myClassUnderTest.performGetUpData1("data")
    }

    @Test
    void testGetFoo() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo2() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo1() {
        assert "result" == myClassUnderTest.getUpperFoo1("key")
    }
}
