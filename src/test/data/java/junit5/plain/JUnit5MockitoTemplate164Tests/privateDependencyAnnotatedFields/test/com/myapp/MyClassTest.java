package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.Socket;
import java.net.SocketImplFactory;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private ExecutorService mockExecutorService;
    @Mock
    private SocketImplFactory mockSocketFactory;

    @InjectMocks
    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testCreateNewConnection() throws Exception {
        assertEquals(new Socket("host", 80), myClassUnderTest.createNewConnection());
    }
}
