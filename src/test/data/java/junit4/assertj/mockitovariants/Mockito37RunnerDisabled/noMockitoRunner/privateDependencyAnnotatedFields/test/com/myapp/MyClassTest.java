package com.myapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.SocketImplFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory;

    @InjectMocks
    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @Before
    public void setUp() {
        mockitoCloseable = openMocks(this);
        // TODO: Set the following fields: mExecutorService, mSocketFactory.
    }

    @After
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCreateNewConnection() {
        assertThat(myClassUnderTest.createNewConnection()).isNull();
    }
}
