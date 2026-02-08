package com.myapp;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.SocketImplFactory;

import static org.testng.Assert.assertNull;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory;

    @InjectMocks
    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        // TODO: Set the following fields: mExecutorService, mSocketFactory.
    }

    @Test
    public void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}
