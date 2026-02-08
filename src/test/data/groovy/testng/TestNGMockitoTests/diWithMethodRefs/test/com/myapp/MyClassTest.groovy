package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @BeforeMethod
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
        when(mockFoo.capitalize("str")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.testInternalMethodRefThatCallsDep(["value"])

        // Verify the results
        assert ["value"] == result
    }
}
