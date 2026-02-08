package com.myapp;

import com.myapp.bases.SubFooService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private SubFooService mockSubFooService;

    @Test
    void testGetFoo() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockSubFooService);
        when(mockSubFooService.getData("key")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo_SubFooServiceThrowsIOException() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(mockSubFooService);
        when(mockSubFooService.getData("key")).thenThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo2() throws Exception {
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
    void testGetFoo2_FooServiceGetOtherDataThrowsIOException() throws Exception {
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
