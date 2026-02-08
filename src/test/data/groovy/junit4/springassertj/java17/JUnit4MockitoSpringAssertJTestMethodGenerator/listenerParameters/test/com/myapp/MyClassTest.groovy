package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.util.concurrent.ExecutorService

import static org.mockito.Mockito.mock

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private ExecutorService mockExecutorServiceToUseForThings
    @Mock
    private Object mockDefaultListener

    private MyClass myClassUnderTest

    @Before
    void setUp() {
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