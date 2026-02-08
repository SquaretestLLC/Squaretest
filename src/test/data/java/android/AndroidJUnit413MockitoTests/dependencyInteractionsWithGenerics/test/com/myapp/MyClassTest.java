package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.myapp.other.GenericFoo;
import com.myapp.other.GenericOtherFoo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private GenericFoo<String> mockProducer;
    @Mock
    private GenericFoo<? extends CharSequence> mockOtherProducer;
    @Mock
    private GenericOtherFoo<? extends Map<?, ?>> mockMapFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockProducer, mockOtherProducer, mockMapFoo);
    }

    @Test
    public void testCreateTheString() throws Exception {
        // Setup
        when(mockProducer.produceT()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createTheString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testCreateTheString_GenericFooThrowsIOException() throws Exception {
        // Setup
        when(mockProducer.produceT()).thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.createTheString());
    }

    @Test
    public void testCreateTheOtherString() throws Exception {
        // Setup
        when(mockOtherProducer.produceT()).thenReturn("result");

        // Run the test
        final CharSequence result = myClassUnderTest.createTheOtherString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testCreateTheOtherString_GenericFooThrowsIOException() throws Exception {
        // Setup
        when(mockOtherProducer.produceT()).thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.createTheOtherString());
    }

    @Test
    public void testGetObjectFromMap() throws Exception {
        // Setup
        when(mockMapFoo.getValue()).thenReturn(new HashMap<>());

        // Run the test
        final Object result = myClassUnderTest.getObjectFromMap();

        // Verify the results
    }

    @Test
    public void testGetObjectFromMap_GenericOtherFooThrowsIOException() throws Exception {
        // Setup
        when(mockMapFoo.getValue()).thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getObjectFromMap());
    }
}
