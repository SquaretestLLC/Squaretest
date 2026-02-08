package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @Before
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @After
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testMakeString() {
        // Setup
        // Configure Foo.openCloseableFoo(...).
        def spyCloseableFoo = spy(new CloseableFoo(new ByteArrayInputStream("content".getBytes())))
        when(mockFoo.openCloseableFoo("source")).thenReturn(spyCloseableFoo)

        // Run the test
        def result = myClassUnderTest.makeString()

        // Verify the results
        assert "result" == result
        verify(spyCloseableFoo).close()
    }
}
