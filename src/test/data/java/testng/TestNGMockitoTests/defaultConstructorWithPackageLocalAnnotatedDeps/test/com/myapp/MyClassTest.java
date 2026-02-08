package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.SocketImplFactory;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertNull;

public class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory;

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass();
        myClassUnderTest.mExecutorService = MoreExecutors.newDirectExecutorService();
        myClassUnderTest.mSocketFactory = mockSocketFactory;
    }

    @Test
    public void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}
