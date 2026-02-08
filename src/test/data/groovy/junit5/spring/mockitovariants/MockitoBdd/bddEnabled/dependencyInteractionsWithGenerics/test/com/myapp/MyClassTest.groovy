package com.myapp

import com.myapp.other.GenericFoo
import com.myapp.other.GenericOtherFoo
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.junit.jupiter.api.Assertions.assertThrows
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

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockProducer, mockOtherProducer, mockMapFoo)
    }

    @Test
    void testCreateTheString() {
        // Setup
        given(mockProducer.produceT()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testCreateTheString_GenericFooThrowsIOException() {
        // Setup
        given(mockProducer.produceT()).willThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.createTheString()
        })
    }

    @Test
    void testCreateTheOtherString() {
        // Setup
        given(mockOtherProducer.produceT()).willReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheOtherString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testCreateTheOtherString_GenericFooThrowsIOException() {
        // Setup
        given(mockOtherProducer.produceT()).willThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.createTheOtherString()
        })
    }

    @Test
    void testGetObjectFromMap() {
        // Setup
        given(mockMapFoo.getValue()).willReturn(["value": ["value"]])

        // Run the test
        def result = myClassUnderTest.getObjectFromMap()

        // Verify the results
    }

    @Test
    void testGetObjectFromMap_GenericOtherFooThrowsIOException() {
        // Setup
        given(mockMapFoo.getValue()).willThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.getObjectFromMap()
        })
    }
}
