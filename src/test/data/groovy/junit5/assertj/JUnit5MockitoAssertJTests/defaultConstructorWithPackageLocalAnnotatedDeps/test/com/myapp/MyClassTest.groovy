package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass()
        myClassUnderTest.mExecutorService = MoreExecutors.newDirectExecutorService()
        myClassUnderTest.mSocketFactory = mockSocketFactory
    }

    @Test
    void testCreateNewConnection() {
        assertThat(myClassUnderTest.createNewConnection()).isNull()
    }
}
