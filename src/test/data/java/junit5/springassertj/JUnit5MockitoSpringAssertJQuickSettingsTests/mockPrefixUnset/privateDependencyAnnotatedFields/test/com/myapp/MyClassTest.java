package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.SocketImplFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private SocketImplFactory mSocketFactory;

    @InjectMocks
    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        // TODO: Set the following fields: mExecutorService, mSocketFactory.
    }

    @Test
    void testCreateNewConnection() {
        assertThat(myClassUnderTest.createNewConnection()).isNull();
    }
}
