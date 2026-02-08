package com.myapp

import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.net.Socket
import java.net.SocketImplFactory
import java.util.concurrent.ExecutorService

import static org.testng.Assert.assertEquals
import static org.mockito.Mockito.mock

class MyClassTest {

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.mExecutorService = MoreExecutors.newDirectExecutorService()
        myClassUnderTest.mSocketFactory = mock(SocketImplFactory.class)
        myClassUnderTest.mTimeout = 0L
    }

    @Test
    void testCreateNewConnection() throws Exception {
        // Setup
        final Socket expectedResult = new Socket("host", 80)

        // Run the test
        final Socket result = myClassUnderTest.createNewConnection()

        // Verify the results
        assertEquals(expectedResult, result)
    }
}
