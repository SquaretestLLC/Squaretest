package com.myapp;

import org.junit.Before;
import org.junit.Test;

import java.net.SocketImplFactory;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass();
        myClassUnderTest.mSocketFactory = mock(SocketImplFactory.class);
    }

    @Test
    public void testCreateNewConnection1() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}