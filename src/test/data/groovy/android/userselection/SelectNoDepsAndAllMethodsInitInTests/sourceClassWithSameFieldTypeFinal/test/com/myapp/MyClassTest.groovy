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
    void testGetFooById() {
        // Setup
        def myClassUnderTest = new MyClass(new FooService(), new MyClass(new FooService(), null))

        // Run the test
        def result = myClassUnderTest.getFooById("id")

        // Verify the results
    }
}
