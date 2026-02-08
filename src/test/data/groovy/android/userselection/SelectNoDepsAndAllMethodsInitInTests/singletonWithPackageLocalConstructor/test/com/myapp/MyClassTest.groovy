package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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
