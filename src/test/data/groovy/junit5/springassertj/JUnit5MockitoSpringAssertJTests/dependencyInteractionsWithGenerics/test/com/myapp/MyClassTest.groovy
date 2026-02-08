package com.myapp

import com.myapp.other.GenericFoo
import com.myapp.other.GenericOtherFoo
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.when
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

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockProducer, mockOtherProducer, mockMapFoo)
    }

    @Test
    void testCreateTheString() {
        // Setup
        when(mockProducer.produceT()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheString()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testCreateTheString_GenericFooThrowsIOException() {
        // Setup
        when(mockProducer.produceT()).thenThrow(IOException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.createTheString()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCreateTheOtherString() {
        // Setup
        when(mockOtherProducer.produceT()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheOtherString()

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testCreateTheOtherString_GenericFooThrowsIOException() {
        // Setup
        when(mockOtherProducer.produceT()).thenThrow(IOException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.createTheOtherString()
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testGetObjectFromMap() {
        // Setup
        when(mockMapFoo.getValue()).thenReturn(["value": ["value"]])

        // Run the test
        def result = myClassUnderTest.getObjectFromMap()

        // Verify the results
    }

    @Test
    void testGetObjectFromMap_GenericOtherFooThrowsIOException() {
        // Setup
        when(mockMapFoo.getValue()).thenThrow(IOException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getObjectFromMap()
        }).isInstanceOf(IOException.class)
    }
}
