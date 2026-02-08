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
    void testGetFooAndBar11() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, "defaultBarId")

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}