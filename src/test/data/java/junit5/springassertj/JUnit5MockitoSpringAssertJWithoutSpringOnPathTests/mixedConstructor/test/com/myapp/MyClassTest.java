package com.myapp;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.net.SocketImplFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockSocketFactory, 0L);
    }

    @Test
    void testCreateNewConnection() {
        assertThat(myClassUnderTest.createNewConnection()).isNull();
    }
}
