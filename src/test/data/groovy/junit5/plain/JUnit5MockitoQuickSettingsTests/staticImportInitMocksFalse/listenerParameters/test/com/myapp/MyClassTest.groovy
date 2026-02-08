package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), "defaultListener")
    }

    @Test
    void testPerformOperationWithListenerUnused() {
        // Setup
        // Run the test
        myClassUnderTest.performOperationWithListenerUnused(null)

        // Verify the results
    }

    @Test
    void testPerformOperation() {
        // Setup
        def mockListener = Mockito.mock(EventListener.class)

        // Run the test
        myClassUnderTest.performOperation(mockListener)

        // Verify the results
    }
}
