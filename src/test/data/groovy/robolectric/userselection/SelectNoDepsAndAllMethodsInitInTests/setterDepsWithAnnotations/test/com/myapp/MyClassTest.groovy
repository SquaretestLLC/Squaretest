package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testGetFooAndBar1() {
        // Setup
        def myClassUnderTest = new MyClass()
        myClassUnderTest.setFooService(null)
        myClassUnderTest.setBarService(null)
        myClassUnderTest.setDefaultBarId("defaultBarId")

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
