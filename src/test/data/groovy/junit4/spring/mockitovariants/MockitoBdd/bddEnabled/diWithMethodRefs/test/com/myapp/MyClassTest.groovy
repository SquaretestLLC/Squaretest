package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

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
        assert ["value"] == result
    }

    @Test
    void testTestDiStaticMethodRef() {
        assert ["value"] == myClassUnderTest.testDiStaticMethodRef(["value"])
        assert [] == myClassUnderTest.testDiStaticMethodRef(["value"])
    }

    @Test
    void testTestInternalMethodRef() {
        assert ["value"] == myClassUnderTest.testInternalMethodRef(["value"])
        assert [] == myClassUnderTest.testInternalMethodRef(["value"])
    }

    @Test
    void testTestInternalMethodRefThatCallsDep() {
        // Setup
        given(mockFoo.capitalize("str")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.testInternalMethodRefThatCallsDep(["value"])

        // Verify the results
        assert ["value"] == result
    }
}
