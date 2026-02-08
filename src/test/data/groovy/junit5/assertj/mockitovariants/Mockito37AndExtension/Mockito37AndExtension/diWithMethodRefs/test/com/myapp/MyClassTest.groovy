package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when

@CompileStatic
@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testTestDiMethodRef() {
        // Setup
        when(mockFoo.capitalize("str")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.testDiMethodRef(["value"])

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }

    @Test
    void testTestDiStaticMethodRef() {
        assertThat(myClassUnderTest.testDiStaticMethodRef(["value"])).isEqualTo(["value"])
        assertThat(myClassUnderTest.testDiStaticMethodRef(["value"])).isEqualTo([])
    }

    @Test
    void testTestInternalMethodRef() {
        assertThat(myClassUnderTest.testInternalMethodRef(["value"])).isEqualTo(["value"])
        assertThat(myClassUnderTest.testInternalMethodRef(["value"])).isEqualTo([])
    }

    @Test
    void testTestInternalMethodRefThatCallsDep() {
        // Setup
        when(mockFoo.capitalize("str")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.testInternalMethodRefThatCallsDep(["value"])

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }
}
