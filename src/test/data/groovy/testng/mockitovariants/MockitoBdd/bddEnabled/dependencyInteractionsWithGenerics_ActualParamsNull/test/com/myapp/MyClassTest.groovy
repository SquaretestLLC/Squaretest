package com.myapp

import com.myapp.other.GenericFoo
import com.myapp.other.GenericOtherFoo
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.ArgumentMatchers.any
import static org.mockito.BDDMockito.given
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private GenericFoo<String> mockProducer
    @Mock
    private GenericFoo<? extends CharSequence> mockOtherProducer
    @Mock
    private GenericOtherFoo<? extends Map<?, ?>> mockMapFoo

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockProducer, mockOtherProducer, mockMapFoo)
    }

    @Test
    void testCreateTheString() {
        // Setup
        given(mockProducer.produceT(any(T.class))).willReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheString()

        // Verify the results
        assert "result" == result
    }

    @Test(expectedExceptions = [IOException.class])
    void testCreateTheString_GenericFooThrowsIOException() {
        // Setup
        given(mockProducer.produceT(any(T.class))).willThrow(IOException.class)

        // Run the test
        myClassUnderTest.createTheString()
    }

    @Test
    void testCreateTheOtherString() {
        // Setup
        given(mockOtherProducer.produceT(any(T.class))).willReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheOtherString()

        // Verify the results
        assert "result" == result
    }

    @Test(expectedExceptions = [IOException.class])
    void testCreateTheOtherString_GenericFooThrowsIOException() {
        // Setup
        given(mockOtherProducer.produceT(any(T.class))).willThrow(IOException.class)

        // Run the test
        myClassUnderTest.createTheOtherString()
    }

    @Test
    void testGetObjectFromMap() {
        // Setup
        given(mockMapFoo.getValue(any(T.class))).willReturn(["value": ["value"]])

        // Run the test
        def result = myClassUnderTest.getObjectFromMap()

        // Verify the results
    }

    @Test(expectedExceptions = [IOException.class])
    void testGetObjectFromMap_GenericOtherFooThrowsIOException() {
        // Setup
        given(mockMapFoo.getValue(any(T.class))).willThrow(IOException.class)

        // Run the test
        myClassUnderTest.getObjectFromMap()
    }
}
