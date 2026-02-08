package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private Producer<String> mockProducer;

    private MyClass<String> myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass<>(mockProducer);
    }

    @Test
    public void testCreateNewT1() {
        // Setup
        when(mockProducer.createNewT("theString")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewT("theString");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testCreateNewT2() {
        // Setup
        when(mockProducer.createNewT("templateT")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewT("templateT");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testCreateNewK1() {
        // Setup
        when(mockProducer.createNewK("templateK")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewK("templateK");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testCreateNewK2() {
        assertNull(myClassUnderTest.createNewK2("templateK"));
    }

    @Test
    public void testCreateNewK2() {
        // Setup
        when(mockProducer.createNewK(String.class, "key")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewK(String.class, "key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testCreateNewBar() {
        // Setup
        when(mockProducer.createNewK(any(MyClass.Bar.class))).thenReturn(new MyClass.Bar());

        // Run the test
        final MyClass.Bar result = myClassUnderTest.createNewBar();

        // Verify the results
    }

    @Test
    public void testCreateNewBar2() {
        // Setup
        when(mockProducer.createNewK(MyClass.Bar.class, "bob")).thenReturn(new MyClass.Bar());

        // Run the test
        final MyClass.Bar result = myClassUnderTest.createNewBar2();

        // Verify the results
    }

    @Test
    public void testCreateNewGenericBar() {
        // Setup
        final MyClass.Bar bar = new MyClass.Bar();
        when(mockProducer.createNewK(any(MyClass.GenericBar.class)))
                .thenReturn(new MyClass.GenericBar<>(new MyClass.Bar()));

        // Run the test
        final MyClass.GenericBar<MyClass.Bar> result = myClassUnderTest.createNewGenericBar(bar);

        // Verify the results
    }

    @Test
    public void testCreateNewGenericBar2() {
        // Setup
        when(mockProducer.createNewK(MyClass.GenericBar.class, "bob")).thenReturn(new MyClass.GenericBar<>("t"));

        // Run the test
        final MyClass.GenericBar result = myClassUnderTest.createNewGenericBar2(MyClass.GenericBar.class);

        // Verify the results
    }
}
