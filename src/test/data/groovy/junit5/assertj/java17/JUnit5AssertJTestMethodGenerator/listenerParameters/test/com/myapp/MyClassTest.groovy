package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.util.concurrent.ExecutorService

import static org.mockito.Mockito.mock
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private ExecutorService mockExecutorServiceToUseForThings
    @Mock
    private Object mockDefaultListener

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockExecutorServiceToUseForThings, mockDefaultListener)
    }

    @Test
    void testPerformOperation1() {
        // Setup
        def mockListener = mock(Object.class)

        // Run the test
        myClassUnderTest.performOperation(mockListener)

        // Verify the results
    }
}