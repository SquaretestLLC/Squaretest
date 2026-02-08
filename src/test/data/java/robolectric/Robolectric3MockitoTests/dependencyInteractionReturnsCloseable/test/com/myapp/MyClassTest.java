package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
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
