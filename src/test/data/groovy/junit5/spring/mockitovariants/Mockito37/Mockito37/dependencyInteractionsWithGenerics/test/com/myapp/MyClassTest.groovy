package com.myapp

import com.myapp.other.GenericFoo
import com.myapp.other.GenericOtherFoo
import groovy.transform.CompileStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private GenericFoo<String> mockProducer
    @Mock
    private GenericFoo<? extends CharSequence> mockOtherProducer
    @Mock
    private GenericOtherFoo<? extends Map<?, ?>> mockMapFoo

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockProducer, mockOtherProducer, mockMapFoo)
    }

    @AfterEach
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testCreateTheString() {
        // Setup
        when(mockProducer.produceT()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testCreateTheString_GenericFooThrowsIOException() {
        // Setup
        when(mockProducer.produceT()).thenThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.createTheString()
        })
    }

    @Test
    void testCreateTheOtherString() {
        // Setup
        when(mockOtherProducer.produceT()).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.createTheOtherString()

        // Verify the results
        assert "result" == result
    }

    @Test
    void testCreateTheOtherString_GenericFooThrowsIOException() {
        // Setup
        when(mockOtherProducer.produceT()).thenThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.createTheOtherString()
        })
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
        assertThrows(IOException.class, {
            myClassUnderTest.getObjectFromMap()
        })
    }
}
