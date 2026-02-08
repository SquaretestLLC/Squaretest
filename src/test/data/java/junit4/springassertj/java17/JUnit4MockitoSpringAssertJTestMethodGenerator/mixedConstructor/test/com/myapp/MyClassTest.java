package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.SocketImplFactory;
import java.util.concurrent.ExecutorService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private ExecutorService mockExecutorService;
    @Mock
    private SocketImplFactory mockSocketFactory;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockExecutorService, mockSocketFactory, 0L);
    }

    @Test
    public void testCreateNewConnection1() {
        assertThat(myClassUnderTest.createNewConnection()).isNull();
    }
}