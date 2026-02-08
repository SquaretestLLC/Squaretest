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
    void testDoSomething() {
        // Setup
        def myClassUnderTest = new MyClass()
        def myFoo = null

        // Run the test
        myClassUnderTest.doSomething(myFoo)

        // Verify the results
    }
}
