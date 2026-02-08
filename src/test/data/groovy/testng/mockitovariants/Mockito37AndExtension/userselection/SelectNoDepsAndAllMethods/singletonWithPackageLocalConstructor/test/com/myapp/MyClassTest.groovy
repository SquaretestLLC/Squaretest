package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), null, 0L)
    }

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
        // Run the test
        def result = myClassUnderTest.createNewConnection()

        // Verify the results
    }
}
