package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.util.concurrent.ExecutorService

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private ExecutorService mockExecutorService
    @Mock
    private SocketImplFactory mockSocketFactory

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockExecutorService, mockSocketFactory, 0L)
    }

    @Test
    void testCreateNewConnection1() {
        assertThat(myClassUnderTest.createNewConnection()).isNull()
    }
}