package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    void testTestDiMethodRef() {
        // Setup
        given(mockFoo.capitalize("str")).willReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.testDiMethodRef(Arrays.asList("value"));

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testTestDiStaticMethodRef() {
        assertThat(myClassUnderTest.testDiStaticMethodRef(Arrays.asList("value"))).isEqualTo(Arrays.asList("value"));
        assertThat(myClassUnderTest.testDiStaticMethodRef(Arrays.asList("value"))).isEqualTo(Collections.emptyList());
    }

    @Test
    void testTestInternalMethodRef() {
        assertThat(myClassUnderTest.testInternalMethodRef(Arrays.asList("value"))).isEqualTo(Arrays.asList("value"));
        assertThat(myClassUnderTest.testInternalMethodRef(Arrays.asList("value"))).isEqualTo(Collections.emptyList());
    }

    @Test
    void testTestInternalMethodRefThatCallsDep() {
        // Setup
        given(mockFoo.capitalize("str")).willReturn("result");

        // Run the test
        final List<String> result = myClassUnderTest.testInternalMethodRefThatCallsDep(Arrays.asList("value"));

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }
}
