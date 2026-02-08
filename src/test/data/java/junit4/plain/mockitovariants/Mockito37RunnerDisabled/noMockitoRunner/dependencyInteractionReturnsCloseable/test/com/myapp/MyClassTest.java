package com.myapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @Before
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @After
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testMakeString() throws Exception {
        // Setup
        // Configure Foo.openCloseableFoo(...).
        final CloseableFoo spyCloseableFoo = spy(new CloseableFoo(new ByteArrayInputStream("content".getBytes())));
        when(mockFoo.openCloseableFoo("source")).thenReturn(spyCloseableFoo);

        // Run the test
        final String result = myClassUnderTest.makeString();

        // Verify the results
        assertEquals("result", result);
        verify(spyCloseableFoo).close();
    }
}
