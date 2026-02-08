package com.myapp;

import com.myapp.other.GenericFoo;
import com.myapp.other.GenericOtherFoo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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
        myClassUnderTest = new MyClass(mockProducer, mockOtherProducer, mockMapFoo);
    }

    @Test
    public void testCreateTheString() throws Exception {
        // Setup
        when(mockProducer.produceT()).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createTheString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testCreateTheString_GenericFooThrowsIOException() throws Exception {
        // Setup
        when(mockProducer.produceT()).thenThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createTheString()).isInstanceOf(IOException.class);
    }

    @Test
    public void testCreateTheOtherString() throws Exception {
        // Setup
        when(mockOtherProducer.produceT()).thenReturn("result");

        // Run the test
        final CharSequence result = myClassUnderTest.createTheOtherString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testCreateTheOtherString_GenericFooThrowsIOException() throws Exception {
        // Setup
        when(mockOtherProducer.produceT()).thenThrow(IOException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createTheOtherString()).isInstanceOf(IOException.class);
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
        assertThatThrownBy(() -> myClassUnderTest.getObjectFromMap()).isInstanceOf(IOException.class);
    }
}
