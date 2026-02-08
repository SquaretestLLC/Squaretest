package com.myapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testLeftGetterAndSetter() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        final String left = "setKeyParam";
        myClassUnderTest.setKey(left);
        assertEquals(left, myClassUnderTest.getKey());
    }

    @Test
    public void testGetValue() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        assertEquals("result", myClassUnderTest.getValue());
    }

    @Test
    public void testSetValue() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        assertEquals("result", myClassUnderTest.setValue("setValueParam"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSetValue_ThrowsUnsupportedOperationException() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expected = ClassCastException.class)
    public void testSetValue_ThrowsClassCastException() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expected = NullPointerException.class)
    public void testSetValue_ThrowsNullPointerException() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetValue_ThrowsIllegalArgumentException() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        myClassUnderTest.setValue("setValueParam");
    }

    @Test(expected = IllegalStateException.class)
    public void testSetValue_ThrowsIllegalStateException() {
        final MyClass<String, String> myClassUnderTest = new MyClass<>();
        myClassUnderTest.setValue("setValueParam");
    }
}
