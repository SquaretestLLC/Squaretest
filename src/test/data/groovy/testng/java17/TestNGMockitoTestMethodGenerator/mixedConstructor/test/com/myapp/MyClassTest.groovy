package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.util.concurrent.ExecutorService

import static org.mockito.MockitoAnnotations.initMocks
import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    @Mock
    private ExecutorService mockExecutorService
    @Mock
    private SocketImplFactory mockSocketFactory

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockExecutorService, mockSocketFactory, 0L)
    }

    @Test
    void testCreateNewConnection1() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}