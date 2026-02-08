package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.net.SocketImplFactory;
import java.util.concurrent.ExecutorService;

import static org.junit.Assert.assertNull;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private ExecutorService mockExecutorService;
    @Mock
    private SocketImplFactory mockSocketFactory;

    @InjectMocks
    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        // TODO: Set the following fields: mSocketFactory.
    }

    @Test
    public void testCreateNewConnection1() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}