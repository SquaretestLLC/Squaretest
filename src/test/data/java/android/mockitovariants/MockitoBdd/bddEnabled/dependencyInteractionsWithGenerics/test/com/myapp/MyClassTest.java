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
import static org.mockito.BDDMockito.given;
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
        given(mockProducer.produceT()).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.createTheString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = IOException.class)
    public void testCreateTheString_GenericFooThrowsIOException() throws Exception {
        // Setup
        given(mockProducer.produceT()).willThrow(IOException.class);

        // Run the test
        myClassUnderTest.createTheString();
    }

    @Test
    public void testCreateTheOtherString() throws Exception {
        // Setup
        given(mockOtherProducer.produceT()).willReturn("result");

        // Run the test
        final CharSequence result = myClassUnderTest.createTheOtherString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test(expected = IOException.class)
    public void testCreateTheOtherString_GenericFooThrowsIOException() throws Exception {
        // Setup
        given(mockOtherProducer.produceT()).willThrow(IOException.class);

        // Run the test
        myClassUnderTest.createTheOtherString();
    }

    @Test
    public void testGetObjectFromMap() throws Exception {
        // Setup
        given(mockMapFoo.getValue()).willReturn(new HashMap<>());

        // Run the test
        final Object result = myClassUnderTest.getObjectFromMap();

        // Verify the results
    }

    @Test(expected = IOException.class)
    public void testGetObjectFromMap_GenericOtherFooThrowsIOException() throws Exception {
        // Setup
        given(mockMapFoo.getValue()).willThrow(IOException.class);

        // Run the test
        myClassUnderTest.getObjectFromMap();
    }
}
