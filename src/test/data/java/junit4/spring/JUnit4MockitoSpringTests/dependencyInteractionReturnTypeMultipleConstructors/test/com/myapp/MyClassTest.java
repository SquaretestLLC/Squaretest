package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testGetDecoder() {
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
