package com.myapp;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.SocketImplFactory;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertNull;

public class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory;

    @InjectMocks
    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        // TODO: Set the following fields: mExecutorService, mSocketFactory.
    }

    @Test
    public void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}
