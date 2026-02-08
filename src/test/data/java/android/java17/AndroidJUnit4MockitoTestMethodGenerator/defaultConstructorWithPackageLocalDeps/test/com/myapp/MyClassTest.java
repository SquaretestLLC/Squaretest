package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.SocketImplFactory;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
@SmallTest
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