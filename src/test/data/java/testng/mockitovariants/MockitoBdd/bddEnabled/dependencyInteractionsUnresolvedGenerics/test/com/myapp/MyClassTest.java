package com.myapp;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class MyClassTest {

    @Mock
    private Producer<String> mockProducer;

    private MyClass<String> myClassUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass<>(mockProducer);
    }

    @Test
    public void testCreateNewT1() {
        // Setup
        given(mockProducer.createNewT("theString")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewT("theString");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testCreateNewT2() {
        // Setup
        given(mockProducer.createNewT("templateT")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewT("templateT");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testCreateNewK1() {
        // Setup
        given(mockProducer.createNewK("templateK")).willReturn("result");

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
        given(mockProducer.createNewK(String.class, "key")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewK(String.class, "key");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testCreateNewBar() {
        // Setup
        given(mockProducer.createNewK(any(MyClass.Bar.class))).willReturn(new MyClass.Bar());

        // Run the test
        final MyClass.Bar result = myClassUnderTest.createNewBar();

        // Verify the results
    }

    @Test
    public void testCreateNewBar2() {
        // Setup
        given(mockProducer.createNewK(MyClass.Bar.class, "bob")).willReturn(new MyClass.Bar());

        // Run the test
        final MyClass.Bar result = myClassUnderTest.createNewBar2();

        // Verify the results
    }

    @Test
    public void testCreateNewGenericBar() {
        // Setup
        final MyClass.Bar bar = new MyClass.Bar();
        given(mockProducer.createNewK(any(MyClass.GenericBar.class)))
                .willReturn(new MyClass.GenericBar<>(new MyClass.Bar()));

        // Run the test
        final MyClass.GenericBar<MyClass.Bar> result = myClassUnderTest.createNewGenericBar(bar);

        // Verify the results
    }

    @Test
    public void testCreateNewGenericBar2() {
        // Setup
        given(mockProducer.createNewK(MyClass.GenericBar.class, "bob")).willReturn(new MyClass.GenericBar<>("t"));

        // Run the test
        final MyClass.GenericBar result = myClassUnderTest.createNewGenericBar2(MyClass.GenericBar.class);

        // Verify the results
    }
}
