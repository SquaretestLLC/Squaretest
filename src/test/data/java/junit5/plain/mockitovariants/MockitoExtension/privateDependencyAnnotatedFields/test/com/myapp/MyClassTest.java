package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.SocketImplFactory;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory;

    @InjectMocks
    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        // TODO: Set the following fields: mExecutorService, mSocketFactory.
    }

    @Test
    void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection());
    }
}
