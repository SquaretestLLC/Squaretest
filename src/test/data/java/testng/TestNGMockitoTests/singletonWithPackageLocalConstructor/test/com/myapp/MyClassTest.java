package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.SocketImplFactory;
import java.net.SocketOptions;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockSocketFactory, 0L);
    }

    @Test
    public void testGetInstance() {
        // Setup
        // Run the test
        final MyClass result = MyClass.getInstance();

        // Verify the results
    }

    @Test
    public void testCreateNewConnection() {
        // Setup
        when(mockSocketFactory.createSocketImpl()).thenReturn(null);

        // Run the test
        final SocketOptions result = myClassUnderTest.createNewConnection();

        // Verify the results
    }
}
