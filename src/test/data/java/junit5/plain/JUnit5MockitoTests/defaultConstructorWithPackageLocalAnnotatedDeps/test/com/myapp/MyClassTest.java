package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.net.SocketImplFactory;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass();
        myClassUnderTest.mExecutorService = MoreExecutors.newDirectExecutorService();
        myClassUnderTest.mSocketFactory = mockSocketFactory;
    }

    @Test
    void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}
