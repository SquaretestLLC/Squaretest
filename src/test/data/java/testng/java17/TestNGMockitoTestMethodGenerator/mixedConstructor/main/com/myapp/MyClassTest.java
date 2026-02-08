package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.mockito.Mock;

import java.net.Socket;
import java.net.SocketImplFactory;
import java.util.concurrent.ExecutorService;

import static org.testng.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private ExecutorService mockExecutorService;
    @Mock
    private SocketImplFactory mockSocketFactory;

    private MyClass myClassUnderTest;

    @BeforeMethod
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockExecutorService, mockSocketFactory, 0L);
    }

    @Test
    void testCreateNewConnection() throws Exception {
        // Setup
        final Socket expectedResult = new Socket("host", 80);

        // Run the test
        final Socket result = myClassUnderTest.createNewConnection();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
