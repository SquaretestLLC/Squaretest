package com.myapp;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;

@Listeners(MockitoTestNGListener.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeMethod
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
