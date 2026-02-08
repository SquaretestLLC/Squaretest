package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.testng.Assert.assertEquals;

public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeMethod
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testTestDiMethodRef() {
        // Setup
        when(mockFoo.capitalize("str")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.testDiMethodRef(Arrays.asList("value"));

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    public void testTestDiStaticMethodRef() {
        assertEquals(Arrays.asList("value"), myClassUnderTest.testDiStaticMethodRef(Arrays.asList("value")));
        assertEquals(Collections.emptyList(), myClassUnderTest.testDiStaticMethodRef(Arrays.asList("value")));
    }

    @Test
    public void testTestInternalMethodRef() {
        assertEquals(Arrays.asList("value"), myClassUnderTest.testInternalMethodRef(Arrays.asList("value")));
        assertEquals(Collections.emptyList(), myClassUnderTest.testInternalMethodRef(Arrays.asList("value")));
    }

    @Test
    public void testTestInternalMethodRefThatCallsDep() {
        // Setup
        when(mockFoo.capitalize("str")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.testInternalMethodRefThatCallsDep(Arrays.asList("value"));

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }
}
