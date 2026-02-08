package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.net.SocketImplFactory;

import static org.junit.Assert.assertNull;
import static org.mockito.MockitoAnnotations.openMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @Before
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockSocketFactory, 0L);
    }

    @After
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}
