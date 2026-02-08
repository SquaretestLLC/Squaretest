package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.SocketImplFactory;

import static org.junit.Assert.assertNull;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private SocketImplFactory mSocketFactory;

    @InjectMocks
    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        // TODO: Set the following fields: mExecutorService, mSocketFactory.
    }

    @Test
    public void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}
