package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.mock

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private EventListener defaultListener

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), defaultListener)
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
        def mockListener = mock(EventListener.class)

        // Run the test
        myClassUnderTest.performOperation(mockListener)

        // Verify the results
    }
}
