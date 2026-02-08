package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.BDDMockito.given

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testTestDiMethodRef() {
        // Setup
        given(mockFoo.capitalize("str")).willReturn("result")

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
        given(mockFoo.capitalize("str")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.testInternalMethodRefThatCallsDep(["value"])

        // Verify the results
        assertThat(result).isEqualTo(["value"])
    }
}
