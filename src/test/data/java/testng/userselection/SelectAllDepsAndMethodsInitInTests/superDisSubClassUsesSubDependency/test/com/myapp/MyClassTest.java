package com.myapp;

import com.myapp.bases.SubFooService;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class MyClassTest {

    @Mock
    private SubFooService mockSubFooService;

    @BeforeMethod
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
