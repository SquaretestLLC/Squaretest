package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock

import java.util.concurrent.ExecutorService

import static org.junit.jupiter.api.Assertions.assertNull
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private ExecutorService mockExecutorService
    @Mock
    private SocketImplFactory mockSocketFactory

    @InjectMocks
    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        // TODO: Set the following fields: mSocketFactory.
    }

    @Test
    void testCreateNewConnection1() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}