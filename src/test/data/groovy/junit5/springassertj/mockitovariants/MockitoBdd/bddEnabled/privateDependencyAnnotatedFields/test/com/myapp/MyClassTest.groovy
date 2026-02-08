package com.myapp

import com.google.common.util.concurrent.MoreExecutors
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.util.ReflectionTestUtils

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private SocketImplFactory mockSocketFactory

    @InjectMocks
    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        ReflectionTestUtils.setField(myClassUnderTest, "mExecutorService", MoreExecutors.newDirectExecutorService())
        // TODO: Set the following fields: mSocketFactory.
    }

    @Test
    void testCreateNewConnection() {
        assertThat(myClassUnderTest.createNewConnection()).isNull()
    }
}
