package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.myapp.bases.SubFooService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private SubFooService mockSubFooService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetFoo() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockSubFooService);
        when(mockSubFooService.getData("key")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo_SubFooServiceThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockSubFooService);
        when(mockSubFooService.getData("key")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testGetFoo2() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockSubFooService);
        when(mockSubFooService.getOtherData("key")).thenReturn("result");
        when(mockSubFooService.doSomething("key")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetFoo2_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockSubFooService);
        when(mockSubFooService.getOtherData("key")).thenThrow(IOException.class);
        when(mockSubFooService.doSomething("key")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("key");

        // Verify the results
        assertEquals("result", result);
    }
}
