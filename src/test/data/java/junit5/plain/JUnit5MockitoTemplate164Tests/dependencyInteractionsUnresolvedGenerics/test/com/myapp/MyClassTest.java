package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Producer<String> mockProducer;

    private MyClass<String> myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass<>(mockProducer);
    }

    @Test
    void testCreateNewT1() {
        // Setup
        when(mockProducer.createNewT("theString")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewT("theString");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCreateNewT2() {
        // Setup
        when(mockProducer.createNewT("templateT")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewT("templateT");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCreateNewK1() {
        // Setup
        when(mockProducer.createNewK("templateK")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewK("templateK");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCreateNewK2() {
        assertEquals("result", myClassUnderTest.createNewK2("templateK"));
    }

    @Test
    void testCreateNewK2() {
        // Setup
        when(mockProducer.createNewK(String.class, "key")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewK(String.class, "key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCreateNewBar() {
        // Setup
        when(mockProducer.createNewK(any(MyClass.Bar.class))).thenReturn(new MyClass.Bar());

        // Run the test
        final MyClass.Bar result = myClassUnderTest.createNewBar();

        // Verify the results
    }

    @Test
    void testCreateNewBar2() {
        // Setup
        when(mockProducer.createNewK(MyClass.Bar.class, "bob")).thenReturn(new MyClass.Bar());

        // Run the test
        final MyClass.Bar result = myClassUnderTest.createNewBar2();

        // Verify the results
    }

    @Test
    void testCreateNewGenericBar() {
        // Setup
        final MyClass.Bar bar = new MyClass.Bar();
        when(mockProducer.createNewK(any(MyClass.GenericBar.class)))
                .thenReturn(new MyClass.GenericBar<>(new MyClass.Bar()));

        // Run the test
        final MyClass.GenericBar<MyClass.Bar> result = myClassUnderTest.createNewGenericBar(bar);

        // Verify the results
    }

    @Test
    void testCreateNewGenericBar2() {
        // Setup
        when(mockProducer.createNewK(MyClass.GenericBar.class, "bob")).thenReturn(new MyClass.GenericBar<>("t"));

        // Run the test
        final MyClass.GenericBar result = myClassUnderTest.createNewGenericBar2(MyClass.GenericBar.class);

        // Verify the results
    }
}
