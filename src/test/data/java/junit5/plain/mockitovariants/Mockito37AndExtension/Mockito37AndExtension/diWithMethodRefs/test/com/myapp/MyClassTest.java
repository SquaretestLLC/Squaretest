package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockFoo);
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
