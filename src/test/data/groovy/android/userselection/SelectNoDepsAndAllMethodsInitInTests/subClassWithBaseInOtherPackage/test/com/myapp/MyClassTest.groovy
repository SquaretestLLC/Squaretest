package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Test
    void testGetItems1() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.getItems()

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testGetItems2() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.getItems("criteria")

        // Verify the results
        assert ["value"] == result
    }

    @Test
    void testGetItems3() {
        // Setup
        def myClassUnderTest = new MyClass(null)

        // Run the test
        def result = myClassUnderTest.getItems("criteria", "otherData", "thirdData", "fourthData")

        // Verify the results
        assert ["value"] == result
    }
}
