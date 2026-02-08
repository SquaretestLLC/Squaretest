package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    void testGetDecoder() {
        // Setup
        // Configure Foo.getDecoder(...).
        final XMLDecoder spyXMLDecoder = spy(new XMLDecoder(new ByteArrayInputStream("content".getBytes()), "owner"));
        when(mockFoo.getDecoder("name")).thenReturn(spyXMLDecoder);

        // Run the test
        final XMLDecoder result = myClassUnderTest.getDecoder("name");

        // Verify the results
        verify(spyXMLDecoder).close();
    }
}
