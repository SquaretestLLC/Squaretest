package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(new FooService(), new MyClass(new FooService(), null))
    }

    @Test
    void testGetFooById() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }
}
