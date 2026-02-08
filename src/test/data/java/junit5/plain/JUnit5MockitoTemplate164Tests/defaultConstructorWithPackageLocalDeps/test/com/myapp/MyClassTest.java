package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Socket;
import java.net.SocketImplFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
        myClassUnderTest.mExecutorService = MoreExecutors.newDirectExecutorService();
        myClassUnderTest.mSocketFactory = mock(SocketImplFactory.class);
        myClassUnderTest.mTimeout = 0L;
    }

    @Test
    void testCreateNewConnection() throws Exception {
        assertEquals(new Socket("host", 80), myClassUnderTest.createNewConnection());
    }
}
