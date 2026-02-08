package com.myapp;

import com.myapp.bases.SubFooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private SubFooService mockSubFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockSubFooService);
    }

    @Test
    void testGetFoo() throws Exception {
        // Setup
        given(mockSubFooService.getData("key")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo_SubFooServiceThrowsIOException() throws Exception {
        // Setup
        given(mockSubFooService.getData("key")).willThrow(IOException.class);

        // Run the test
        final String result = myClassUnderTest.getFoo("key");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo2() throws Exception {
        // Setup
        given(mockSubFooService.getOtherData("key")).willReturn("result");
        given(mockSubFooService.doSomething("key")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo2_FooServiceGetOtherDataThrowsIOException() throws Exception {
        // Setup
        given(mockSubFooService.getOtherData("key")).willThrow(IOException.class);
        given(mockSubFooService.doSomething("key")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("key");

        // Verify the results
        assertEquals("result", result);
    }
}
