package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.Mockito.mock
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private EventListener defaultListener

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
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
