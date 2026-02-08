package com.myapp


import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testGetFoo11() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.getFoo1("id")

        // Verify the results
    }
}