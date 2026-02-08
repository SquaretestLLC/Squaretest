package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.beans.XMLDecoder

import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @AfterEach
    void tearDown() {
        mockitoCloseable.close()
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
