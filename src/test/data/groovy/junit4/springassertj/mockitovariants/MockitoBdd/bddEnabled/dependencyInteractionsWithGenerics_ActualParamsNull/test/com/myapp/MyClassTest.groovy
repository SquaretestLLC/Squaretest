package com.myapp

import com.myapp.other.GenericFoo
import com.myapp.other.GenericOtherFoo
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.ArgumentMatchers.any
import static org.mockito.BDDMockito.given

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
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
        given(mockProducer.produceT(any(T.class))).willReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheString()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testCreateTheString_GenericFooThrowsIOException() {
        // Setup
        given(mockProducer.produceT(any(T.class))).willThrow(IOException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.createTheString()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCreateTheOtherString() {
        // Setup
        given(mockOtherProducer.produceT(any(T.class))).willReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheOtherString()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testCreateTheOtherString_GenericFooThrowsIOException() {
        // Setup
        given(mockOtherProducer.produceT(any(T.class))).willThrow(IOException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.createTheOtherString()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testGetObjectFromMap() {
        // Setup
        given(mockMapFoo.getValue(any(T.class))).willReturn(["value": ["value"]])

        // Run the test
        def result = myClassUnderTest.getObjectFromMap()

        // Verify the results
    }

    @Test
    void testGetObjectFromMap_GenericOtherFooThrowsIOException() {
        // Setup
        given(mockMapFoo.getValue(any(T.class))).willThrow(IOException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getObjectFromMap()
        }).isInstanceOf(IOException.class)
    }
}
