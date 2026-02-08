package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.squaretest.supertypes.base.FooService
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Test
    void testPerformGetUpData() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.performGetUpData("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testPerformGetUpData1() {
        def myClassUnderTest = new MyClass(new FooService())
        assert "result" == myClassUnderTest.performGetUpData1("data")
    }

    @Test
    void testGetFoo() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo2() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getFoo2("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService())

        // Run the test
        def result = myClassUnderTest.getUpperFoo("data")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetUpperFoo1() {
        def myClassUnderTest = new MyClass(new FooService())
        assert "result" == myClassUnderTest.getUpperFoo1("key")
    }
}
