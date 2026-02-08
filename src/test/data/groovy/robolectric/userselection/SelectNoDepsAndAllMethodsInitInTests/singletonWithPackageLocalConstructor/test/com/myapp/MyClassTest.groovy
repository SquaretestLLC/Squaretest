package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testGetInstance() {
        // Setup
        // Run the test
        def result = MyClass.getInstance()

        // Verify the results
    }

    @Test
    void testCreateNewConnection() {
        // Setup
        def myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), null, 0L)

        // Run the test
        def result = myClassUnderTest.createNewConnection()

        // Verify the results
    }
}
