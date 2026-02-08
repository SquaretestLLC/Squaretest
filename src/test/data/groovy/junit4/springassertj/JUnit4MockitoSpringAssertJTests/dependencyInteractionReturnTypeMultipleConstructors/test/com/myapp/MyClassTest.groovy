package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.beans.XMLDecoder

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
    void testGetDecoder() {
        // Setup
        // Configure Foo.getDecoder(...).
        def spyXMLDecoder = spy(new XMLDecoder(new ByteArrayInputStream("content".getBytes()), "owner"))
        when(mockFoo.getDecoder("name")).thenReturn(spyXMLDecoder)

        // Run the test
        def result = myClassUnderTest.getDecoder("name")

        // Verify the results
        verify(spyXMLDecoder).close()
    }
}
