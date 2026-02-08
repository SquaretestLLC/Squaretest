package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.myapp.other.GenericFoo
import com.myapp.other.GenericOtherFoo
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
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
        initMocks(this)
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

    @Test(expected = IOException.class)
    void testCreateTheString_GenericFooThrowsIOException() {
        // Setup
        when(mockProducer.produceT(any(T.class))).thenThrow(IOException.class)

        // Run the test
        myClassUnderTest.createTheString()
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

    @Test(expected = IOException.class)
    void testCreateTheOtherString_GenericFooThrowsIOException() {
        // Setup
        when(mockOtherProducer.produceT(any(T.class))).thenThrow(IOException.class)

        // Run the test
        myClassUnderTest.createTheOtherString()
    }

    @Test
    void testGetObjectFromMap() {
        // Setup
        when(mockMapFoo.getValue(any(T.class))).thenReturn(["value": ["value"]])

        // Run the test
        def result = myClassUnderTest.getObjectFromMap()

        // Verify the results
    }

    @Test(expected = IOException.class)
    void testGetObjectFromMap_GenericOtherFooThrowsIOException() {
        // Setup
        when(mockMapFoo.getValue(any(T.class))).thenThrow(IOException.class)

        // Run the test
        myClassUnderTest.getObjectFromMap()
    }
}
