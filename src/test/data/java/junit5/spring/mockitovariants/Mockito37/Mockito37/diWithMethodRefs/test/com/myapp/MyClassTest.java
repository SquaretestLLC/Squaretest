package com.myapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testTestDiMethodRef() {
        // Setup
        when(mockFoo.capitalize("str")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.testDiMethodRef(Arrays.asList("value"));

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testTestDiStaticMethodRef() {
        assertEquals(Arrays.asList("value"), myClassUnderTest.testDiStaticMethodRef(Arrays.asList("value")));
        assertEquals(Collections.emptyList(), myClassUnderTest.testDiStaticMethodRef(Arrays.asList("value")));
    }

    @Test
    void testTestInternalMethodRef() {
        assertEquals(Arrays.asList("value"), myClassUnderTest.testInternalMethodRef(Arrays.asList("value")));
        assertEquals(Collections.emptyList(), myClassUnderTest.testInternalMethodRef(Arrays.asList("value")));
    }

    @Test
    void testTestInternalMethodRefThatCallsDep() {
        // Setup
        when(mockFoo.capitalize("str")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.testInternalMethodRefThatCallsDep(Arrays.asList("value"));

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }
}
