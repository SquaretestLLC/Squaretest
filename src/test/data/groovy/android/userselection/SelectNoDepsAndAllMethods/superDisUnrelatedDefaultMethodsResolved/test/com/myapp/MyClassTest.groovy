package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
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
