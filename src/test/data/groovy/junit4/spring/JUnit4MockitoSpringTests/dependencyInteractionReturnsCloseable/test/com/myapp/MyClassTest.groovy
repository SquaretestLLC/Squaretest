package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFoo)
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
