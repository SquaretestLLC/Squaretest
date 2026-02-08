package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.util.concurrent.ExecutorService

import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private ExecutorService mockExecutorService
    @Mock
    private SocketImplFactory mockSocketFactory

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
        def myClassUnderTest = new MyClass(mockExecutorService, mockSocketFactory, 0L)
        when(mockSocketFactory.createSocketImpl()).thenReturn(null)

        // Run the test
        def result = myClassUnderTest.createNewConnection()

        // Verify the results
    }
}
