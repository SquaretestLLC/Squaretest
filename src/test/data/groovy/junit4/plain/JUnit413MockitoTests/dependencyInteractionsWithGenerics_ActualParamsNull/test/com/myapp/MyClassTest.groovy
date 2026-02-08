package com.myapp

import com.myapp.other.GenericFoo
import com.myapp.other.GenericOtherFoo
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.junit.Assert.assertThrows
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.when

@CompileStatic
@RunWith(MockitoJUnitRunner.class)
class MyClassTest {

    @Mock
    private GenericFoo<String> mockProducer
    @Mock
    private GenericFoo<? extends CharSequence> mockOtherProducer
    @Mock
    private GenericOtherFoo<? extends Map<?, ?>> mockMapFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockProducer, mockOtherProducer, mockMapFoo)
    }

    @Test
    void testCreateTheString() {
        // Setup
        when(mockProducer.produceT(any(T.class))).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testCreateTheString_GenericFooThrowsIOException() {
        // Setup
        when(mockProducer.produceT(any(T.class))).thenThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.createTheString()
        })
    }

    @Test
    void testCreateTheOtherString() {
        // Setup
        when(mockOtherProducer.produceT(any(T.class))).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheOtherString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testCreateTheOtherString_GenericFooThrowsIOException() {
        // Setup
        when(mockOtherProducer.produceT(any(T.class))).thenThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.createTheOtherString()
        })
    }

    @Test
    void testGetObjectFromMap() {
        // Setup
        when(mockMapFoo.getValue(any(T.class))).thenReturn(["value": ["value"]])

        // Run the test
        def result = myClassUnderTest.getObjectFromMap()

        // Verify the results
    }

    @Test
    void testGetObjectFromMap_GenericOtherFooThrowsIOException() {
        // Setup
        when(mockMapFoo.getValue(any(T.class))).thenThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.getObjectFromMap()
        })
    }
}
