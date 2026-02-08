package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import java.util.concurrent.ExecutorService

import static org.mockito.Mockito.when

@CompileStatic
@ExtendWith(MockitoExtension.class)
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
