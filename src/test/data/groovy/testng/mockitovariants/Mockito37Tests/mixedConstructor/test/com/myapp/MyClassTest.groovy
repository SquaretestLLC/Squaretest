package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.MockitoAnnotations.openMocks
import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeMethod
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(MoreExecutors.newDirectExecutorService(), mockSocketFactory, 0L)
    }

    @AfterMethod
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testCreateNewConnection() {
        assertNull(myClassUnderTest.createNewConnection())
    }
}
