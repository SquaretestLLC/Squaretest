package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.net.SocketImplFactory;

import static org.junit.Assert.assertNull;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockSocketFactory, 0L);
    }

    @Test
    public void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}
