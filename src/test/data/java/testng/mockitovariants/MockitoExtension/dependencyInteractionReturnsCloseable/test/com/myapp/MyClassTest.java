package com.myapp;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

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
