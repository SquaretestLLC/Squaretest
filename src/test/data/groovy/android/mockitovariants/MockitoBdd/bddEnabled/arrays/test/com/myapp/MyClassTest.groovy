package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
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
        myClassUnderTest = new MyClass([0] as int[], ["stringArray"] as String[], [] as Socket[][])
    }

    @Test
    void testCompareArray() {
        myClassUnderTest.compareArray(["array"] as String[], [] as Socket[][])
    }

    @Test
    void testReturnTheArray() {
        assert ["result"] as String[] == myClassUnderTest.returnTheArray()
    }

    @Test
    void testReturnTheArrays() {
        // Setup
        // Run the test
        def result = myClassUnderTest.returnTheArrays()

        // Verify the results
    }
}
