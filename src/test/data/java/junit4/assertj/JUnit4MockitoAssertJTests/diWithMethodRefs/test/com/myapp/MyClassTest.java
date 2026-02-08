package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testTestDiMethodRef() {
        // Setup
        when(mockFoo.capitalize("str")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.testDiMethodRef(Arrays.asList("value"));

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    public void testTestDiStaticMethodRef() {
        assertThat(myClassUnderTest.testDiStaticMethodRef(Arrays.asList("value"))).isEqualTo(Arrays.asList("value"));
        assertThat(myClassUnderTest.testDiStaticMethodRef(Arrays.asList("value"))).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testTestInternalMethodRef() {
        assertThat(myClassUnderTest.testInternalMethodRef(Arrays.asList("value"))).isEqualTo(Arrays.asList("value"));
        assertThat(myClassUnderTest.testInternalMethodRef(Arrays.asList("value"))).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testTestInternalMethodRefThatCallsDep() {
        // Setup
        when(mockFoo.capitalize("str")).thenReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.testInternalMethodRefThatCallsDep(Arrays.asList("value"));

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }
}
