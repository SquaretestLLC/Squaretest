package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.SocketImplFactory;
import java.util.concurrent.ExecutorService;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private ExecutorService mockExecutorService;
    @Mock
    private SocketImplFactory mockSocketFactory;

    @InjectMocks
    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        // TODO: Set the following fields: mSocketFactory.
    }

    @Test
    public void testCreateNewConnection1() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}