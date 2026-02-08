package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.net.SocketImplFactory;
import java.net.SocketOptions;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private ExecutorService mockExecutorService;
    @Mock
    private SocketImplFactory mockSocketFactory;

    @Before
    public void setUp() {
        initMocks(this);
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
        final MyClass myClassUnderTest = new MyClass(mockExecutorService, mockSocketFactory, 0L);
        when(mockSocketFactory.createSocketImpl()).thenReturn(null);

        // Run the test
        final SocketOptions result = myClassUnderTest.createNewConnection();

        // Verify the results
    }
}
