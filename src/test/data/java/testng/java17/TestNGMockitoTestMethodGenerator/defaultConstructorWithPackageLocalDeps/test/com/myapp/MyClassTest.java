package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.SocketImplFactory;

import static org.mockito.Mockito.mock;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        myClassUnderTest = new MyClass();
        myClassUnderTest.mSocketFactory = mock(SocketImplFactory.class);
    }

    @Test
    public void testCreateNewConnection1() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}