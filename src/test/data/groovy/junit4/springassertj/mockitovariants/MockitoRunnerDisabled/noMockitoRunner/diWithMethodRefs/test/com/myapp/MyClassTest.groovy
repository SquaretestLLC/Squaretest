package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
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
